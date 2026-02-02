package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.dto.GameWithPlayingExperienceDto;
import com.example.demo.model.vo.game.GameWithPlayingExperienceVo;
import com.example.demo.model.dos.GamePlayingExperience;
import org.apache.ibatis.annotations.Mapper;

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

    static List<GameWithPlayingExperienceVo> toResponseList(List<GameWithPlayingExperienceDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return List.of();
        }

        // 按 gameId 分组并收集每组的 playingExperienceContext，使用 LinkedHashMap 保持出现顺序
        var grouped = dtoList.stream()
                .collect(Collectors.groupingBy(
                        GameWithPlayingExperienceDto::getGameId,
                        LinkedHashMap::new,
                        Collectors.mapping(GameWithPlayingExperienceDto::getPlayingExperienceContext, Collectors.toList())
                ));

        // 将分组结果映射为响应对象列表
        return grouped.entrySet().stream()
                .map(entry -> new GameWithPlayingExperienceVo(
                        entry.getKey(),
                        // 取分组中第一个的 gameName（从原列表中查找）
                        dtoList.stream().filter(d -> d.getGameId() == entry.getKey()).findFirst().map(GameWithPlayingExperienceDto::getGameName).orElse(null),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }

    static GameWithPlayingExperienceVo toResponseForGame(List<GameWithPlayingExperienceDto> dtos, long gameId){
        List<GameWithPlayingExperienceDto> filtered = dtos == null ? List.of()
                : dtos.stream().filter(f -> f.getGameId() == gameId).toList();
        if(filtered.isEmpty()) return new GameWithPlayingExperienceVo();

        GameWithPlayingExperienceDto first = filtered.get(0);
        List<String> experiences = filtered.stream()
                .map(GameWithPlayingExperienceDto::getPlayingExperienceContext)
                .toList();
        return new GameWithPlayingExperienceVo(gameId, first.getGameName(), experiences);
    }
}
