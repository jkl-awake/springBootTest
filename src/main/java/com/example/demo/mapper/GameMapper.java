package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Games;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GameMapper extends BaseMapper<Games> {

    /**
     * 获取包含游戏玩法体验的游戏信息
     * get game with its playing experience
     * */
    @Select("""
        select g.* from game g 
           left join game_playing_experience gpe on g.id = gpe.game_id
           where g.is_deleted = 0
""")
    Games getGameWithPlayingExperience();
}
