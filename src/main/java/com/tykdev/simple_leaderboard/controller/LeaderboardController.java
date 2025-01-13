package com.tykdev.simple_leaderboard.controller;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.service.LeaderboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaderboardController {

    private final LeaderboardService leaderboardService;
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/")
    public String index() {
        return "Hello, World. I am FYHOJ leaderboard!";
    }

    @GetMapping("/highScoreLeaderboard")
    public List<PlayerRecordDto> getHighScoreLeaderboard() {
        return leaderboardService.getHighScoreLeaderboardDto();
    }

    @GetMapping("/highLevelLeaderboard")
    public List<PlayerRecordDto> getHighLevelLeaderboard() {
        return leaderboardService.getHighLevelLeaderboardDto();
    }

    @GetMapping("/topTenHighScoreLeaderboard")
    public List<PlayerRecordDto> getTopTenHighScoreLeaderboard() {
        return leaderboardService.getTopTenHighScoreLeaderboardDto();
    }

    @GetMapping("/topTenHighLevelLeaderboard")
    public List<PlayerRecordDto> getTopTenHighLevelLeaderboard() {
        return leaderboardService.getTopTenHighLevelLeaderboardDto();
    }

}
