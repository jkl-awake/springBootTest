package com.example.demo.converter;

import com.example.demo.common.enums.PlatformEnum;
import com.example.demo.model.bo.*;
import com.example.demo.model.dos.Games;
import com.example.demo.model.dto.*;
import com.example.demo.model.vo.game.GameWithPlayingExperienceVo;
import com.example.demo.model.vo.game.GamesVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameConverter {
    GetGamesBo toBo(GetGamesDto getGamesDto);
    GameCreateBo toBo(GameCreateDto gameCreateDto);
    GameUpdateBo toBo(GameUpdateDto gameUpdateDto);
    default PlatformEnum map(int platform) {
        return PlatformEnum.fromCode(platform);
    }

    GameWithPlayingExperienceBo toBo(GameWithPlayingExperienceDto dto);
    PlayingExperienceOperateBo toBo(PlayingExperienceOperateDto dto);
    GameOperateBo toBo(GameOperateDto dto);

    //vo
    @Mapping(source = "gameName", target = "name")
    GamesVo toVo(Games games);

    GameWithPlayingExperienceVo toVo(GameWithPlayingExperienceBo bo);
}
