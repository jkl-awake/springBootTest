package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameWithPlayingExperienceDto {
    private long gameId;
    private String gameName;
    private long playingExperienceId;
    private String playingExperienceContext;
}

