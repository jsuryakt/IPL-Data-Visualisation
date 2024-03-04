package com.jsuryakt.ipldashboard.repository;

import com.jsuryakt.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByTeamNameIgnoreCase(String teamName);
}
