package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.StudyTab;
import com.example.demo.request.CreateOrUpdateStudyTabRequest;
import com.example.demo.request.StudyTabPageRequest;
import com.example.demo.response.ApiResponse;

public interface IStudyTab {
    ApiResponse<IPage<StudyTab>> GetStudyTabPage(StudyTabPageRequest request);

    ApiResponse<Integer> CreateOrUpdateStudyTab(CreateOrUpdateStudyTabRequest request);

    ApiResponse<Integer> DeleteStudyTab(Long id);
}