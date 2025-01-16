package com.tykdev.simple_leaderboard.service.implementation;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.model.PlayerRecord;
import com.tykdev.simple_leaderboard.repository.LeaderboardRepository;
import com.tykdev.simple_leaderboard.service.LeaderboardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public PlayerRecord getPlayerRecord(String playerNameAndDiscriminator) {
        String username = getUsernameFromPlayerNameAndDiscriminator(playerNameAndDiscriminator);
        int discriminator = getDiscriminatorFromPlayerNameAndDiscriminator(playerNameAndDiscriminator);
        return leaderboardRepository.findByUsernameAndDiscriminator(
                username,
                discriminator)
                .orElseThrow(() -> new RuntimeException("Player record not found for username: " + username +
                " and discriminator: " + discriminator));
    }

    //This is called by controller, it calls getPlayerRecord and then converts it to DTO
    @Override
    public PlayerRecordDto getPlayerRecordDto(String playerNameAndDiscriminator) {
        return convertToDto(getPlayerRecord(playerNameAndDiscriminator));
    }

    //TODO
    @Override
    public PlayerRecordDto addPlayerRecord(PlayerRecordDto playerRecordDto) {
        return null;
    }

    @Override
    public String getUsernameFromPlayerNameAndDiscriminator(String playerNameAndDiscriminator) {
        String[] parts = splitAndValidate(playerNameAndDiscriminator);
        return parts[0]; // Return username
    }

    @Override
    public int getDiscriminatorFromPlayerNameAndDiscriminator(String playerNameAndDiscriminator) {
        String[] parts = splitAndValidate(playerNameAndDiscriminator);
        return Integer.parseInt(parts[1]); // Return discriminator
    }

    // Helper method to split and validate the input
    private String[] splitAndValidate(String playerNameAndDiscriminator) {
        if (playerNameAndDiscriminator == null || !playerNameAndDiscriminator.contains("#")) {
            throw new IllegalArgumentException("Invalid format. Expected 'username#discriminator'.");
        }
        String[] parts = playerNameAndDiscriminator.split("#");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format. Expected 'username#discriminator'.");
        }
        return parts;
    }

    //TODO
    @Override
    public List<PlayerRecord> getFriendsLeaderboard() {
        return null;
    }

    //TODO
    @Override
    public List<PlayerRecordDto> getFriendsLeaderboardDto() {
        return null;
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
