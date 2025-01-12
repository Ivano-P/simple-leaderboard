package com.tykdev.simple_leaderboard.controller;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.model.PlayerRecord;
import com.tykdev.simple_leaderboard.service.LeaderboardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class LeaderboardController {

    private final LeaderboardService leaderboardService;
    @GetMapping("/test")
    public String index() {
        return "Hello, World!";
    }

    @GetMapping("/highScoreLeaderboard")
    public List<PlayerRecordDto> getHighScoreLeaderboard() {
        return leaderboardService.getHighScoreLeaderboardDto();
    }

    @GetMapping("/highLevelLeaderboard")
    public List<PlayerRecordDto> getHighLevelLeaderboard() {
        return leaderboardService.getHighLevelLeaderboardDto();
    }

}
