package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.dos.StudyTab;
import com.example.demo.model.dto.CreateOrUpdateStudyTabDto;
import com.example.demo.model.dto.StudyTabPageDto;
import com.example.demo.common.utils.ApiResponse;
import com.example.demo.model.vo.study.StudyTabVo;

public interface IStudyTab {
    ApiResponse<IPage<StudyTab>> getStudyTabPage(StudyTabPageDto request);

    ApiResponse<StudyTabVo> getStudyTab(StudyTabPageDto dto);

    ApiResponse<Integer> createOrUpdateStudyTab(CreateOrUpdateStudyTabDto request);

    ApiResponse<Integer> deleteStudyTab(Long id);
}