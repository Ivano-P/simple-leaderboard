package com.tykdev.simple_leaderboard.repository;

import com.tykdev.simple_leaderboard.model.PlayerRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaderboardRepository extends JpaRepository<PlayerRecord, Integer> {
    List<PlayerRecord> findTop10ByOrderByHighScoreDesc();
    List<PlayerRecord> findAllByOrderByHighScoreDesc();
    List<PlayerRecord> findTop10ByOrderByHighLevelDesc();
    Optional<PlayerRecord> findByPlayerUuid(UUID playerUuid);
    Optional<PlayerRecord> findByUsernameAndDiscriminator(String username, int discriminator);
}
