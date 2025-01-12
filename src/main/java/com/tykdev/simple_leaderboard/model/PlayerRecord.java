package com.tykdev.simple_leaderboard.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "leaderboard", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "discriminator"}))
@Data
public class PlayerRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_uuid", nullable = false, unique = true, updatable = false, insertable = false)
    private UUID playerUuid;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "discriminator", nullable = false)
    private int discriminator;

    @Column(name = "high_score", nullable = false)
    private int highScore;

    @Column(name = "high_level", nullable = false)
    private int highLevel;

}