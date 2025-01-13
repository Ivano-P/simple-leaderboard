package com.tykdev.simple_leaderboard.service.implementation;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.model.PlayerRecord;
import com.tykdev.simple_leaderboard.repository.LeaderboardRepository;
import com.tykdev.simple_leaderboard.service.LeaderboardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardServiceImpl(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    @Override
    public List<PlayerRecord> getHighScoreLeaderboard() {
        return leaderboardRepository.findAllByOrderByHighScoreDesc();
    }

    @Override
    public List<PlayerRecord> getHighLevelLeaderboard() {
        return leaderboardRepository.findTop10ByOrderByHighLevelDesc();
    }

    @Override
    public List<PlayerRecordDto> getHighScoreLeaderboardDto() {
        List<PlayerRecordDto> leaderboardDto = new ArrayList<>();
        List<PlayerRecord> recordsOfAllPlayersByHighScore = getHighScoreLeaderboard();
        for (PlayerRecord playerRecord : recordsOfAllPlayersByHighScore) {
            leaderboardDto.add(convertToDto(playerRecord));
        }
        return leaderboardDto;
    }


    @Override
    public List<PlayerRecordDto> getHighLevelLeaderboardDto() {
        List<PlayerRecordDto> leaderboardDto = new ArrayList<>();
        List<PlayerRecord> recordsOfAllPlayersByHighLevel = getHighLevelLeaderboard();
        for (PlayerRecord playerRecord : recordsOfAllPlayersByHighLevel) {
            leaderboardDto.add(convertToDto(playerRecord));
        }
        return leaderboardDto;
    }


    @Override
    public List<PlayerRecordDto> getTopTenHighScoreLeaderboardDto() {
        List<PlayerRecord> recordsOfTopTenPlayersByHighScore = leaderboardRepository.findTop10ByOrderByHighScoreDesc();
        List<PlayerRecordDto> leaderboardDto = new ArrayList<>();
        for (PlayerRecord playerRecord : recordsOfTopTenPlayersByHighScore) {
            leaderboardDto.add(convertToDto(playerRecord));
        }
        return leaderboardDto;
    }

    @Override
    public List<PlayerRecordDto> getTopTenHighLevelLeaderboardDto() {
        List<PlayerRecord> recordsOfTopTenPlayersByHighLevel = leaderboardRepository.findTop10ByOrderByHighLevelDesc();
        List<PlayerRecordDto> leaderboardDto = new ArrayList<>();
        for (PlayerRecord playerRecord : recordsOfTopTenPlayersByHighLevel) {
            leaderboardDto.add(convertToDto(playerRecord));
        }
        return leaderboardDto;
    }

    @Override
    public PlayerRecordDto convertToDto(PlayerRecord playerRecord) {
        return new PlayerRecordDto(
                playerRecord.getUsername() + "#" + playerRecord.getDiscriminator(),
                playerRecord.getHighScore(),
                playerRecord.getHighLevel()
        );
    }


}
