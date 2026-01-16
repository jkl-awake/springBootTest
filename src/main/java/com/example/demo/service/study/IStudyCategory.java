package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.StudyCategory;
import com.example.demo.request.CreateOrUpdateStudyCategoryRequest;
import com.example.demo.request.StudyCategoryPageRequest;
import com.example.demo.response.ApiResponse;

public interface IStudyCategory {
    ApiResponse<IPage<StudyCategory>> GetStudyCategoryPage(StudyCategoryPageRequest request);

    ApiResponse<Integer> CreateOrUpdateStudyCategory(CreateOrUpdateStudyCategoryRequest request);

    ApiResponse<Integer> DeleteStudyCategory(Long id);
}
