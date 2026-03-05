package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameWithPlayingExperienceDto implements Serializable {
    private long gameId;
    private String gameName;
    private String gameImage;
    private int star;
    private int platform;
    private String createTime;
    private long playingExperienceId;
    private String playingExperienceContext;
}

