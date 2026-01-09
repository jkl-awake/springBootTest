package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dto.GameWithPlayingExperienceDto;
import com.example.demo.model.Games;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GameMapper extends BaseMapper<Games> {

    /**
     * 获取包含游戏玩法体验的游戏信息
     * get game with its playing experience
     * */
    @Select("""
        select g.id as gameId, g.game_name as gameName, gpe.id as playingExperienceId, gpe.context as playingExperienceContext from games g 
           left join game_playing_experience gpe on g.id = gpe.game_id
           where g.id = #{gameId} and g.is_deleted = false and gpe.is_deleted = false
""")
    List<GameWithPlayingExperienceDto> getGameWithPlayingExperiences(Long gameId);
}
