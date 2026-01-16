package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.StudyArticle;
import com.example.demo.request.CreateOrUpdateStudyArticleRequest;
import com.example.demo.request.StudyArticlePageRequest;
import com.example.demo.response.ApiResponse;

public interface IStudyArticle {
    ApiResponse<IPage<StudyArticle>> GetStudyArticlePage(StudyArticlePageRequest request);

    ApiResponse<Integer> CreateOrUpdateStudyArticle(CreateOrUpdateStudyArticleRequest request);

    ApiResponse<Integer> DeleteStudyArticle(Long id);
}
