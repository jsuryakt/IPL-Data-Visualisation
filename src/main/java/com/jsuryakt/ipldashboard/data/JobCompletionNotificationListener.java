package com.jsuryakt.ipldashboard.data;

import com.jsuryakt.ipldashboard.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    HashMap<String, Team> teamData = new HashMap<>();

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

//            jdbcTemplate
//                    .query("SELECT team1, team2, winner FROM match",
//                            (rs, row) -> "Team 1 " + rs.getString(1) +
//                                    "Team 2 " + rs.getString(2) +
//                                    ", Winner : " + rs.getString(3))
//                    .forEach(System.out::println);

            // get team data for team played first (team1)
            em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            // get team data for team played second (team2)
            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        // get the team if already exist
                        Team team = teamData.get((String) e[0]);
                        // add previous matches count (played as team1) + count of matches played as team2
                        if (team != null) {
                            team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                        } else {
                            // else if it team doesn't exist, create it now
                            team = new Team((String) e[0], (long) e[1]);
                            teamData.put(team.getTeamName(), team);
                        }
                    });

            em.createQuery("select m.winner, count(*) from Match m group by m.winner", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        if (team != null) {
                            team.setTotalWins((long) e[1]);
                        }
                    });
            persistTeams();
        }
    }

    private void persistTeams() {
        // em.persist was not working without this transaction begin and transaction commit
        SessionImplementor sessionImp = (SessionImplementor) em.getDelegate();
        var transaction = sessionImp.getTransaction();
        transaction.begin();
        teamData.values().forEach(em::persist);
        transaction.commit();
    }
}
