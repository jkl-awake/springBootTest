package com.example.demo.model.bo;

import com.example.demo.model.dto.PlayingExperienceOperateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameOperateBo {
    private Long id;
    private String name;
    private String image;
    private int star;
    private int platform;
    private String evaluation;

    private List<PlayingExperienceOperateBo> playingExperiences;
}
