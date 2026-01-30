package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.dos.StudyArticle;
import com.example.demo.model.dto.CreateOrUpdateStudyArticleDto;
import com.example.demo.model.dto.StudyArticlePageDto;
import com.example.demo.response.ApiResponse;

public interface IStudyArticle {
    ApiResponse<IPage<StudyArticle>> GetStudyArticlePage(StudyArticlePageDto request);

    ApiResponse<Integer> CreateOrUpdateStudyArticle(CreateOrUpdateStudyArticleDto request);

    ApiResponse<Integer> DeleteStudyArticle(Long id);
}
