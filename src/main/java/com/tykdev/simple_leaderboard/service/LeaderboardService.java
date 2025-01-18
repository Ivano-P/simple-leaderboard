package com.tykdev.simple_leaderboard.service;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.model.PlayerRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaderboardService {
    List<PlayerRecordDto> getHighScoreLeaderboardDto();
    List<PlayerRecordDto> getHighLevelLeaderboardDto();
    PlayerRecordDto convertToDto(PlayerRecord playerRecord);
    List<PlayerRecordDto> getTopTenHighScoreLeaderboardDto();
    List<PlayerRecordDto> getTopTenHighLevelLeaderboardDto();
    PlayerRecordDto getPlayerRecordDto(String playerNameAndDiscriminator);
    PlayerRecordDto updatePlayerRecordDto(PlayerRecordDto playerRecordDto);
    PlayerRecordDto registerPlayer(String username);
    void deletePlayerRecordDto(String playerNameAndDiscriminator);



}
