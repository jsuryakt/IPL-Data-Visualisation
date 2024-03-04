package com.jsuryakt.ipldashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Match {
    @Id
    private long matchId;
    private String season;
    private LocalDate date;
    private String city;
    private String venue;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String playerOfMatch;
    private String winner;
    private int winnerWickets;
    private int winnerRuns;
    private String outcome;
    private String resultType;
    private String results;
    private String gender;
    private String event;
    private String umpire1;
    private String umpire2;
    private String reserveUmpire;
    private String tvUmpire;
    private String matchReferee;
    private String eliminator;
    private String method;
    private String reserveDate;

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getPlayerOfMatch() {
        return playerOfMatch;
    }

    public void setPlayerOfMatch(String playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getWinnerWickets() {
        return winnerWickets;
    }

    public void setWinnerWickets(int winnerWickets) {
        this.winnerWickets = winnerWickets;
    }

    public int getWinnerRuns() {
        return winnerRuns;
    }

    public void setWinnerRuns(int winnerRuns) {
        this.winnerRuns = winnerRuns;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }

    public String getUmpire2() {
        return umpire2;
    }

    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }

    public String getReserveUmpire() {
        return reserveUmpire;
    }

    public void setReserveUmpire(String reserveUmpire) {
        this.reserveUmpire = reserveUmpire;
    }

    public String getTvUmpire() {
        return tvUmpire;
    }

    public void setTvUmpire(String tvUmpire) {
        this.tvUmpire = tvUmpire;
    }

    public String getMatchReferee() {
        return matchReferee;
    }

    public void setMatchReferee(String matchReferee) {
        this.matchReferee = matchReferee;
    }

    public String getEliminator() {
        return eliminator;
    }

    public void setEliminator(String eliminator) {
        this.eliminator = eliminator;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", date=" + date +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", winner='" + winner + '\'' +
                '}';
    }
}
