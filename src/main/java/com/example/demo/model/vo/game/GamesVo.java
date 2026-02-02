package com.example.demo.model.vo.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesVo implements Serializable {

    private Long gameId;
    private String gameName;
    private List<String> playingExperienceList;
}
