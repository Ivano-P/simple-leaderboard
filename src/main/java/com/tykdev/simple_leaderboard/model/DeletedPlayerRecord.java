package com.tykdev.simple_leaderboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "deleted_leaderboard")
@Data
public class DeletedPlayerRecord {

    @Id
    @Column(name = "player_id")
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "discriminator", nullable = false)
    private int discriminator;

    @Column(name = "high_score")
    private int highScore;

    @Column(name = "high_level")
    private int highLevel;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;
}

