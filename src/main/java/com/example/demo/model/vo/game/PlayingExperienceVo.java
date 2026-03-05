package com.example.demo.model.vo.game;

import com.example.demo.model.bo.GameWithPlayingExperienceBo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayingExperienceVo {
    private long playingExperienceId;
    private String context;
    private String createTime;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static PlayingExperienceVo fromBo(GameWithPlayingExperienceBo bo) {
        PlayingExperienceVo vo = new PlayingExperienceVo();
        vo.setPlayingExperienceId(bo.getPlayingExperienceId());
        vo.setContext(bo.getPlayingExperienceContext());
        if(bo.getPlayingExperienceCreateTime() != null){
            vo.setCreateTime(bo.getPlayingExperienceCreateTime().format(FORMATTER));
        }
        return vo;
    }
}
