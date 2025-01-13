package com.tykdev.simple_leaderboard.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
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

}