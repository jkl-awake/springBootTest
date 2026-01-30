package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.dos.StudyTab;
import com.example.demo.model.dto.CreateOrUpdateStudyTabDto;
import com.example.demo.model.dto.StudyTabPageDto;
import com.example.demo.response.ApiResponse;

public interface IStudyTab {
    ApiResponse<IPage<StudyTab>> GetStudyTabPage(StudyTabPageDto request);

    ApiResponse<Integer> CreateOrUpdateStudyTab(CreateOrUpdateStudyTabDto request);

    ApiResponse<Integer> DeleteStudyTab(Long id);
}