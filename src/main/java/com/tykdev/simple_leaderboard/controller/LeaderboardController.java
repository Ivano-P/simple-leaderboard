package com.tykdev.simple_leaderboard.controller;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.service.LeaderboardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }


    @GetMapping("/highScore")
    public List<PlayerRecordDto> getHighScoreLeaderboard() {
        return leaderboardService.getHighScoreLeaderboardDto();
    }

    @GetMapping("/highLevel")
    public List<PlayerRecordDto> getHighLevelLeaderboard() {
        return leaderboardService.getHighLevelLeaderboardDto();
    }

    @GetMapping("/topTenByScore")
    public List<PlayerRecordDto> getTopTenHighScoreLeaderboard() {
        return leaderboardService.getTopTenHighScoreLeaderboardDto();
    }

    @GetMapping("/topTenByLevel")
    public List<PlayerRecordDto> getTopTenHighLevelLeaderboard() {
        return leaderboardService.getTopTenHighLevelLeaderboardDto();
    }

    @GetMapping("/playerRecord/{usernameAndDiscriminator}")
    public PlayerRecordDto getPlayerRecord(@PathVariable String usernameAndDiscriminator) {
        return leaderboardService.getPlayerRecordDto(usernameAndDiscriminator);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerRecordDto addPlayerRecord(@RequestBody PlayerRecordDto playerRecordDto) {
        return leaderboardService.addPlayerRecord(playerRecordDto);
    }


}
