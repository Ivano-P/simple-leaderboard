package com.tykdev.simple_leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRecordDto {
    private String playerNameAndDiscriminator;
    private int highScore;
    private int highLevel;

}
