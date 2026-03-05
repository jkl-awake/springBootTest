package com.example.demo.service.study;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.bo.CreateOrUpdateStudyTabBo;
import com.example.demo.model.bo.StudyTabPageBo;
import com.example.demo.model.dos.StudyTab;
import com.example.demo.common.utils.ApiResponse;
import com.example.demo.model.vo.study.StudyTabVo;

public interface IStudyTab {
    ApiResponse<IPage<StudyTab>> getStudyTabPage(StudyTabPageBo request);

    ApiResponse<StudyTabVo> getStudyTab(StudyTabPageBo dto);

    ApiResponse<Integer> createOrUpdateStudyTab(CreateOrUpdateStudyTabBo request);

    ApiResponse<Integer> deleteStudyTab(Long id);
}