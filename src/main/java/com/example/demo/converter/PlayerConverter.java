package com.example.demo.converter;

import com.example.demo.model.bo.PlayerCreateBo;
import com.example.demo.model.bo.PlayerUpdateBo;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerConverter {
    PlayerCreateBo toBo(PlayerCreateDto dto);
    PlayerUpdateBo toBo(PlayerUpdateDto dto);
}
