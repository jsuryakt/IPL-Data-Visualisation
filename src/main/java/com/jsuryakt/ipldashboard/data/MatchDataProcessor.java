package com.jsuryakt.ipldashboard.data;

import com.jsuryakt.ipldashboard.model.Match;
import java.time.LocalDate;
import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) {
        final Match match = new Match();

        match.setMatchId(Long.parseLong(matchInput.getMatch_id()));
        match.setSeason(matchInput.getSeason());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setCity(matchInput.getCity());
        match.setVenue(matchInput.getVenue());
        match.setTeam1(matchInput.getTeam1());
        match.setTeam2(matchInput.getTeam2());
        match.setTossDecision(matchInput.getToss_decision());
        match.setTossWinner(matchInput.getToss_winner());
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setWinner(matchInput.getWinner());
        match.setWinnerRuns(Integer.parseInt(matchInput.getWinner_runs()));
        match.setWinnerWickets(Integer.parseInt(matchInput.getWinner_wickets()));
        match.setOutcome(matchInput.getOutcome());
        match.setResults(matchInput.getResults());
        match.setResultType(matchInput.getResult_type());
        match.setGender(matchInput.getGender());
        match.setEvent(matchInput.getEvent());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());
        match.setReserveUmpire(matchInput.getReserve_umpire());
        match.setTvUmpire(matchInput.getTv_umpire());
        match.setMatchReferee(matchInput.getMatch_referee());
        match.setEliminator(matchInput.getEliminator());
        match.setMethod(matchInput.getMethod());
        match.setReserveDate(matchInput.getDate_1());

        return match;
    }

}