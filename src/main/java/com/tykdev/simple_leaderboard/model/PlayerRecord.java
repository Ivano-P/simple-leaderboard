package com.tykdev.simple_leaderboard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "leaderboard", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "discriminator"}))
public class PlayerRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "discriminator", nullable = false)
    private int discriminator;

    @Column(name = "high_score", nullable = false)
    private int highScore;

    @Column(name = "high_level", nullable = false)
    private int highLevel;

    public PlayerRecord(String username) {
        this.username = username;
    }

    public PlayerRecord(String username, int discriminator) {
        this.username = username;
        this.discriminator = discriminator;
    }

    public PlayerRecord(String username, int discriminator, int highScore, int highLevel) {
        this.username = username;
        this.discriminator = discriminator;
        this.highScore = highScore;
        this.highLevel = highLevel;
    }

}