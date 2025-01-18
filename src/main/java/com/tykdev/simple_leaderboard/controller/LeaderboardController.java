package com.tykdev.simple_leaderboard.controller;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.service.LeaderboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    Logger logger = LoggerFactory.getLogger(LeaderboardController.class);

    private final LeaderboardService leaderboardService;
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }


    @GetMapping("/highScore")
    public List<PlayerRecordDto> getHighScoreLeaderboard() {
        logger.info("getHighScoreLeaderboard() called");
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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerRecordDto registerPlayer(@RequestBody String username) {
        return leaderboardService.registerPlayer(username);
    }

    @PutMapping("/updateRecord")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerRecordDto addPlayerRecord(@RequestBody PlayerRecordDto playerRecordDto) {
        return leaderboardService.updatePlayerRecordDto(playerRecordDto);
    }

    @DeleteMapping("/delete/{usernameAndDiscriminator}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayerRecord(@PathVariable String usernameAndDiscriminator) {
        leaderboardService.deletePlayerRecordDto(usernameAndDiscriminator);
    }

}
