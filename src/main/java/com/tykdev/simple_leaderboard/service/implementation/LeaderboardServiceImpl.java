package com.tykdev.simple_leaderboard.service.implementation;

import com.tykdev.simple_leaderboard.dto.PlayerRecordDto;
import com.tykdev.simple_leaderboard.exception.PlayerNotFoundException;
import com.tykdev.simple_leaderboard.model.DeletedPlayerRecord;
import com.tykdev.simple_leaderboard.model.PlayerRecord;
import com.tykdev.simple_leaderboard.repository.DeletedLeaderboardRepository;
import com.tykdev.simple_leaderboard.repository.LeaderboardRepository;
import com.tykdev.simple_leaderboard.service.LeaderboardService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final DeletedLeaderboardRepository deletedLeaderboardRepository;

    public LeaderboardServiceImpl(LeaderboardRepository leaderboardRepository, DeletedLeaderboardRepository deletedLeaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
        this.deletedLeaderboardRepository = deletedLeaderboardRepository;
    }



    private List<PlayerRecord> getHighScoreLeaderboard() {
        return leaderboardRepository.findAllByOrderByHighScoreDesc();
    }

    private List<PlayerRecord> getHighLevelLeaderboard() {
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


    private PlayerRecord getPlayerRecordByNameAndDiscriminator(String playerNameAndDiscriminator) {
        String username = getUsernameFromPlayerNameAndDiscriminator(playerNameAndDiscriminator);
        int discriminator = getDiscriminatorFromPlayerNameAndDiscriminator(playerNameAndDiscriminator);
        return leaderboardRepository.findByUsernameAndDiscriminator(username, discriminator)
                .orElseThrow(() -> new PlayerNotFoundException("Player record not found for username: "
                        + username + " and discriminator: " + discriminator));
    }

    //This is called by controller, it calls getPlayerRecord and then converts it to DTO
    @Override
    public PlayerRecordDto getPlayerRecordDto(String playerNameAndDiscriminator) {
        return convertToDto(getPlayerRecordByNameAndDiscriminator(playerNameAndDiscriminator));
    }


    private String getUsernameFromPlayerNameAndDiscriminator(String playerNameAndDiscriminator) {
        String[] parts = splitAndValidate(playerNameAndDiscriminator);
        return parts[0]; // Return username
    }

    private int getDiscriminatorFromPlayerNameAndDiscriminator(String playerNameAndDiscriminator) {
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

    @Override
    public PlayerRecordDto registerPlayer(String username) {
        return convertToDto(saveNewPlayerRecord(username));
    }

    private PlayerRecord saveNewPlayerRecord(String username) {
        return leaderboardRepository.save(new PlayerRecord(username));
    }

    public PlayerRecordDto updatePlayerRecordDto(PlayerRecordDto playerRecordDto) {
        // Fetch the current record
        PlayerRecord currentRecord = getPlayerRecordByNameAndDiscriminator(playerRecordDto.getPlayerNameAndDiscriminator());

        // Check if an update is needed
        if ( checkIfShouldUpdateRecord(playerRecordDto, currentRecord)) {
            // Update the fields
            currentRecord.setHighScore(playerRecordDto.getHighScore());
            currentRecord.setHighLevel(playerRecordDto.getHighLevel());

            // Save and return updated record
            return convertToDto(leaderboardRepository.save(currentRecord));
        }

        // Return the current record if no update is performed
        return convertToDto(currentRecord);
    }
    private boolean checkIfShouldUpdateRecord(PlayerRecordDto playerRecordDto, PlayerRecord currentRecord) {
        return playerRecordDto.getHighScore() > currentRecord.getHighScore() ||
                playerRecordDto.getHighLevel() > currentRecord.getHighLevel();
    }

    @Override
    public void deletePlayerRecordDto(String usernameAndDiscriminator) {
        PlayerRecord playerRecord = getPlayerRecordByNameAndDiscriminator(usernameAndDiscriminator);

        // Move the record to the deleted table
        deletedLeaderboardRepository.save(convertToDeletedRecord(playerRecord));

        // Delete from the main leaderboard table
        leaderboardRepository.delete(playerRecord);
    }

    private DeletedPlayerRecord convertToDeletedRecord(PlayerRecord playerRecord) {
        DeletedPlayerRecord deletedRecord = new DeletedPlayerRecord();
        deletedRecord.setId(playerRecord.getId());
        deletedRecord.setUsername(playerRecord.getUsername());
        deletedRecord.setDiscriminator(playerRecord.getDiscriminator());
        deletedRecord.setHighScore(playerRecord.getHighScore());
        deletedRecord.setHighLevel(playerRecord.getHighLevel());
        deletedRecord.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        return deletedRecord;
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
