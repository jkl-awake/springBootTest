package com.example.demo.converter;

import com.example.demo.model.bo.GameCreateBo;
import com.example.demo.model.bo.GameUpdateBo;
import com.example.demo.model.bo.GameWithPlayingExperienceBo;
import com.example.demo.model.dto.GameCreateDto;
import com.example.demo.model.dto.GameUpdateDto;
import com.example.demo.model.dto.GameWithPlayingExperienceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameConverter {
    GameCreateBo toBo(GameCreateDto gameCreateDto);

    GameUpdateBo toBo(GameUpdateDto gameUpdateDto);

    GameWithPlayingExperienceBo toBo(GameWithPlayingExperienceDto dto);
}
