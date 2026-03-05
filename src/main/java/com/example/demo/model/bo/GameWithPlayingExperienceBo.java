package com.example.demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameWithPlayingExperienceBo implements Serializable {
    private long gameId;
    private String gameName;
    private String gameImage;
    private int star;
    private int platform;
    private LocalDateTime createdAt;
    private long playingExperienceId;
    private String playingExperienceContext;
    private LocalDateTime playingExperienceCreateTime;
}

