package com.example.demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayingExperienceOperateBo implements Serializable {
    private long gameId;
    private long playingExperienceId;
    private String Context;
}
