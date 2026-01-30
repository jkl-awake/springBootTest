package com.example.demo.converter;

import com.example.demo.model.bo.NotesCreateOrUpdateBo;
import com.example.demo.model.bo.NotesPageBo;
import com.example.demo.model.dto.NotesCreateOrUpdateDto;
import com.example.demo.model.dto.NotesPageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotesConverter {
    NotesCreateOrUpdateBo toBo(NotesCreateOrUpdateDto dto);

    NotesPageBo toBo(NotesPageDto dto);
}
