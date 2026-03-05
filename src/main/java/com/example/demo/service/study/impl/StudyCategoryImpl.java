package com.example.demo.service.study.impl;

import com.example.demo.common.utils.ApiResponse;
import com.example.demo.model.bo.CreateOrUpdateStudyCategoryBo;
import com.example.demo.model.bo.StudyCategoryPageBo;
import com.example.demo.service.study.IStudyCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.StudyCategoryMapper;
import com.example.demo.model.dos.StudyCategory;
import com.example.demo.model.dto.CreateOrUpdateStudyCategoryDto;
import com.example.demo.model.dto.StudyCategoryPageDto;
import com.example.demo.common.utils.JsonUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyCategoryImpl implements IStudyCategory {

    private final StudyCategoryMapper studyCategoryMapper;

    /**
     * 获取study category分页
     * 
     * @param request
     * @return
     */
    @Override
    public ApiResponse<IPage<StudyCategory>> GetStudyCategoryPage(StudyCategoryPageBo request) {
        try {
            IPage<StudyCategory> page = studyCategoryMapper.selectPage(
                    new Page<>(request.getPageNum(), request.getPageSize()),
                    new QueryWrapper<StudyCategory>()
                            .lambda()
                            .eq(StudyCategory::getIsDeleted, false)
                            .eq(request.getTabId() != null, StudyCategory::getTabId, request.getTabId())
                            .orderByDesc(StudyCategory::getCreatedAt));
            return ApiResponse.success(page);
        } catch (Exception e) {
            log.error("get study category page failed, request: {}", JsonUtils.toSilentJson(request), e);
            return ApiResponse.error("get study category page failed");
        }
    }

    /**
     * 创建或更新study category
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ApiResponse<Integer> CreateOrUpdateStudyCategory(CreateOrUpdateStudyCategoryBo request) {
        StudyCategory studyCategory = null;
        try {
            if (request.getId() == null || request.getId() == 0) {
                studyCategory = new StudyCategory();
                studyCategory.setName(request.getName());
                studyCategory.setTabId(request.getTabId());
                int insertCount = studyCategoryMapper.insert(studyCategory);
                return ApiResponse.success(insertCount);
            } else {
                studyCategory = studyCategoryMapper.selectById(request.getId());
                if (studyCategory == null) {
                    return ApiResponse.error("study category not found");
                }
                studyCategory.Update(request.getName());
                int updateCount = studyCategoryMapper.updateById(studyCategory);
                return ApiResponse.success(updateCount);
            }
        } catch (Exception e) {
            log.error("create or update study category failed, studyCategory: {}",JsonUtils.toSilentJson(studyCategory),e);
            return ApiResponse.error("create or update study category failed");
        }
    }

    /**
     * 删除study category
     * 
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ApiResponse<Integer> DeleteStudyCategory(Long id) {
        try {
            int deleteCount = studyCategoryMapper.deleteById(id);
            return ApiResponse.success(deleteCount);
        } catch (Exception e) {
            log.error("delete study category failed, id: {}", id, e);
            return ApiResponse.error("delete study category failed");
        }
    }
}
