package com.example.demo.model.vo.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameWithPlayingExperienceVo {

    private long gameId;
    private String gameName;
    private List<String> playingExperiences;
}
