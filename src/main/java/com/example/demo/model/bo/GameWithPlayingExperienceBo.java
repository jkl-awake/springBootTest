package com.example.demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameWithPlayingExperienceBo implements Serializable {
    private long gameId;
    private String gameName;
    private long playingExperienceId;
    private String playingExperienceContext;
}

