package com.example.demo.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameWithPlayingExperienceResponse {
    private long gameId;
    private String gameName;
    private List<String> playingExperiences;
}
