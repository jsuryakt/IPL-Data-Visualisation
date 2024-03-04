package com.jsuryakt.ipldashboard.controller;

import com.jsuryakt.ipldashboard.model.Match;
import com.jsuryakt.ipldashboard.repository.MatchRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/match")
public class MatchController {

    private final MatchRepository mr;

    public MatchController(MatchRepository mr) {
        this.mr = mr;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return (List<Match>) mr.findAll();
    }
}
