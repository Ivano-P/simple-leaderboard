package com.tykdev.simple_leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerRecordDto {
    private String playerNameAndDiscriminator;
    private int highScore;
    private int highLevel;
}
