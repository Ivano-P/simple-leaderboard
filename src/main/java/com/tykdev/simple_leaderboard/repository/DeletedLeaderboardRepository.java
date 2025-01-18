package com.tykdev.simple_leaderboard.repository;

import com.tykdev.simple_leaderboard.model.DeletedPlayerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedLeaderboardRepository extends JpaRepository<DeletedPlayerRecord, Integer> {
}
