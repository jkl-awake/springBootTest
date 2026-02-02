package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.dos.StudyCategory;
import com.example.demo.model.dto.CreateOrUpdateStudyCategoryDto;
import com.example.demo.model.dto.StudyCategoryPageDto;
import com.example.demo.common.utils.ApiResponse;

public interface IStudyCategory {
    ApiResponse<IPage<StudyCategory>> GetStudyCategoryPage(StudyCategoryPageDto request);

    ApiResponse<Integer> CreateOrUpdateStudyCategory(CreateOrUpdateStudyCategoryDto request);

    ApiResponse<Integer> DeleteStudyCategory(Long id);
}
