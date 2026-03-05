package com.example.demo.converter;

import com.example.demo.model.bo.*;
import com.example.demo.model.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudyConverter {

    //article
    CreateOrUpdateStudyArticleBo toBo(CreateOrUpdateStudyArticleDto dto);
    StudyArticlePageBo toBo(StudyArticlePageDto dto);

    //category
    StudyCategoryPageBo toBo(StudyCategoryPageDto dto);
    CreateOrUpdateStudyCategoryBo toBo(CreateOrUpdateStudyCategoryDto dto);

    //tab
    StudyTabPageBo toBo(StudyTabPageDto dto);
    CreateOrUpdateStudyTabBo toBo(CreateOrUpdateStudyTabDto dto);

}
