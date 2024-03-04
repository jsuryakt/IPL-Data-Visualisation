package com.jsuryakt.ipldashboard.repository;

import com.jsuryakt.ipldashboard.model.Match;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findByTeam1IgnoreCaseOrTeam2IgnoreCaseOrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return findByTeam1IgnoreCaseOrTeam2IgnoreCaseOrderByDateDesc(teamName, teamName, Pageable.ofSize(count));
    }
}
