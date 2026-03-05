package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.common.utils.DateUtils;
import com.example.demo.model.bo.GameWithPlayingExperienceBo;
import com.example.demo.model.dto.GameWithPlayingExperienceDto;
import com.example.demo.model.vo.game.GameWithPlayingExperienceVo;
import com.example.demo.model.dos.GamePlayingExperience;
import com.example.demo.model.vo.game.PlayingExperienceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GamePlayingExperienceMapper extends BaseMapper<GamePlayingExperience> {

//    public static List<gameWithPlayingExperienceResponse> toResponseList(List<gameWithPlayingExperienceDto> dtoList) {
//        HashMap<Long, gameWithPlayingExperienceResponse> responseMap = new HashMap<>();
//
//        for (gameWithPlayingExperienceDto dto : dtoList) {
//            responseMap.putIfAbsent(dto.getGameId(), new gameWithPlayingExperienceResponse(
//                dto.getGameId(),
//                dto.getGameName(),
//                new ArrayList<>()
//            ));
//            responseMap.get(dto.getGameId()).getPlayingExperiences().add(dto.getPlayingExperienceContext());
//        }
//
//        return new ArrayList<>(responseMap.values());
//    }

//    static List<GameWithPlayingExperienceVo> toResponseList(List<GameWithPlayingExperienceDto> dtoList) {
//        if (dtoList == null || dtoList.isEmpty()) {
//            return List.of();
//        }
//
//        // 按 gameId 分组并收集每组的 playingExperienceContext，使用 LinkedHashMap 保持出现顺序
//        var grouped = dtoList.stream()
//                .collect(Collectors.groupingBy(
//                        GameWithPlayingExperienceDto::getGameId,
//                        LinkedHashMap::new,
//                        Collectors.mapping(GameWithPlayingExperienceDto::getPlayingExperienceContext, Collectors.toList())
//                ));
//
//        // 将分组结果映射为响应对象列表
//        return grouped.entrySet().stream().map(
//                entry -> {
//                    GameWithPlayingExperienceDto first = dtoList.stream().filter(f -> f.getGameId() == entry.getKey()).findFirst().orElse(null);
//                    if (first == null) return null;
//                    return new GameWithPlayingExperienceVo(
//                            entry.getKey(),
//                            first.getGameName(),
//                            first.getGameImage(),
//                            first.getStar(),
//                            first.getPlatform(),
//                            first.getCreateTime(),
//                            entry.getValue()
//                    );
//                }
//        ).collect(Collectors.toList());
//    }

    /**
     * 将查询结果转换为响应对象
     * @param bos 查询结果列表
     * @param gameId 游戏ID
     * @return 响应对象
     */
    static GameWithPlayingExperienceVo toResponseForGame(List<GameWithPlayingExperienceBo> bos, long gameId){
        List<GameWithPlayingExperienceBo> filtered = bos == null ? List.of()
                : bos.stream().filter(f -> f.getGameId() == gameId).toList();
        if(filtered.isEmpty()) return new GameWithPlayingExperienceVo();

        GameWithPlayingExperienceBo first = filtered.get(0);
        List<PlayingExperienceVo> experiences = filtered.stream()
                .map(PlayingExperienceVo :: fromBo)
                .toList();
        return new GameWithPlayingExperienceVo(gameId, first.getGameName(), first.getGameImage(), first.getStar(), first.getPlatform(), DateUtils.formatLocalDateWithStartTime(first.getCreatedAt()), experiences);
    }

    /**
     * 根据游戏ID查询未删除的游玩体验
     * @param gameId 游戏ID
     * @return 游玩体验列表
     */
    @Select("SELECT * FROM game_playing_experience WHERE game_id = #{gameId} AND is_deleted = false")
    List<GamePlayingExperience> selectActiveByGameId(Long gameId);
}
