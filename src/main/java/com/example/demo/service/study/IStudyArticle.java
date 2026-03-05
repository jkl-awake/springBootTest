package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.utils.ApiResponse;
import com.example.demo.model.bo.CreateOrUpdateStudyArticleBo;
import com.example.demo.model.bo.StudyArticlePageBo;
import com.example.demo.model.dos.StudyArticle;

public interface IStudyArticle {
    ApiResponse<IPage<StudyArticle>> GetStudyArticlePage(StudyArticlePageBo request);

    ApiResponse<Integer> CreateOrUpdateStudyArticle(CreateOrUpdateStudyArticleBo request);

    ApiResponse<Integer> DeleteStudyArticle(Long id);
}
