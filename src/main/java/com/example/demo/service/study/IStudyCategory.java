package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.bo.CreateOrUpdateStudyCategoryBo;
import com.example.demo.model.bo.StudyCategoryPageBo;
import com.example.demo.model.dos.StudyCategory;
import com.example.demo.common.utils.ApiResponse;

public interface IStudyCategory {
    ApiResponse<IPage<StudyCategory>> GetStudyCategoryPage(StudyCategoryPageBo request);

    ApiResponse<Integer> CreateOrUpdateStudyCategory(CreateOrUpdateStudyCategoryBo request);

    ApiResponse<Integer> DeleteStudyCategory(Long id);
}
