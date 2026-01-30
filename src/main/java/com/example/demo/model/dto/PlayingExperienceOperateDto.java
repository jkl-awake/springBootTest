package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayingExperienceOperateDto implements Serializable {
    private long gameId;
    private long playingExperienceId;
    private String Context;
}
