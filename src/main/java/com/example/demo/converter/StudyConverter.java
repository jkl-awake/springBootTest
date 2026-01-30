package com.example.demo.converter;

import com.example.demo.model.bo.CreateOrUpdateStudyArticleBo;
import com.example.demo.model.bo.StudyArticlePageBo;
import com.example.demo.model.bo.StudyCategoryPageBo;
import com.example.demo.model.bo.StudyTabPageBo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudyConverter {

    CreateOrUpdateStudyArticleBo toBo(CreateOrUpdateStudyArticleBo dto);
    StudyArticlePageBo toBo(StudyArticlePageBo dto);
    StudyCategoryPageBo toBo(StudyCategoryPageBo dto);
    StudyTabPageBo toBo(StudyTabPageBo dto);
}
