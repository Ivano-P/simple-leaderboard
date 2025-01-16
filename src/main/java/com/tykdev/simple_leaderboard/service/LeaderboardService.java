package com.tykdev.simple_leaderboard.service;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.model.PlayerRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaderboardService {
    List<PlayerRecord> getHighScoreLeaderboard();
    List<PlayerRecord> getHighLevelLeaderboard();
    List<PlayerRecordDto> getHighScoreLeaderboardDto();
    List<PlayerRecordDto> getHighLevelLeaderboardDto();
    PlayerRecordDto convertToDto(PlayerRecord playerRecord);
    List<PlayerRecordDto> getTopTenHighScoreLeaderboardDto();
    List<PlayerRecordDto> getTopTenHighLevelLeaderboardDto();
    List<PlayerRecord> getFriendsLeaderboard();
    List<PlayerRecordDto> getFriendsLeaderboardDto();
    PlayerRecord getPlayerRecord(String playerNameAndDiscriminator);
    PlayerRecordDto getPlayerRecordDto(String playerNameAndDiscriminator);
    PlayerRecordDto addPlayerRecord(PlayerRecordDto playerRecordDto);
    String getUsernameFromPlayerNameAndDiscriminator(String playerNameAndDiscriminator);
    int getDiscriminatorFromPlayerNameAndDiscriminator(String playerNameAndDiscriminator);

}
