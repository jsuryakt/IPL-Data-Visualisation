package com.jsuryakt.ipldashboard.data;

import com.jsuryakt.ipldashboard.model.Match;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    private final String[] FIELD_NAMES = new String[]{
            "match_id",
            "season",
            "date",
            "city",
            "venue",
            "team1",
            "team2",
            "toss_winner",
            "toss_decision",
            "player_of_match",
            "winner",
            "winner_wickets",
            "winner_runs",
            "outcome",
            "result_type",
            "results",
            "gender",
            "event",
            "umpire1",
            "umpire2",
            "reserve_umpire",
            "tv_umpire",
            "match_referee",
            "eliminator",
            "method",
            "date_1"
    };

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("matchDataReader")
                .resource(new ClassPathResource("ipl_match_info_data.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .targetType(MatchInput.class)
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .sql("INSERT INTO match (match_id, season, date, city, venue, team1, team2, toss_winner, toss_decision, player_of_match, winner, winner_wickets, winner_runs, outcome, result_type, results, gender, event, umpire1, umpire2, reserve_umpire, tv_umpire, match_referee, eliminator, method, reserve_date) "
                        + "VALUES (:matchId, :season, :date, :city, :venue, :team1, :team2, :tossWinner, :tossDecision, :playerOfMatch, :winner, :winnerWickets, :winnerRuns, :outcome, :resultType, :results, :gender, :event, :umpire1, :umpire2, :reserveUmpire, :tvUmpire, :matchReferee, :eliminator, :method, :reserveDate)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
            FlatFileItemReader<MatchInput> reader, MatchDataProcessor processor, JdbcBatchItemWriter<Match> writer) {
        return new StepBuilder("step1", jobRepository)
                .<MatchInput, Match>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
