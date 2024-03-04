package com.jsuryakt.ipldashboard.controller;

import com.jsuryakt.ipldashboard.model.Team;
import com.jsuryakt.ipldashboard.repository.MatchRepository;
import com.jsuryakt.ipldashboard.repository.TeamRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = TeamController.BASE_PATH)
public class TeamController {
    public static final String BASE_PATH = "/team";

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamNameIgnoreCase(teamName);
        if (team != null) {
            team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 5));
        }
        return team;
    }

    @GetMapping()
    public List<Team> getAllTeams() {
        return (List<Team>) teamRepository.findAll();
    }
}
