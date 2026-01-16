package com.example.demo.service.study;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.StudyTabMapper;
import com.example.demo.model.StudyTab;
import com.example.demo.request.CreateOrUpdateStudyTabRequest;
import com.example.demo.request.StudyTabPageRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.JsonUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyTabImpl implements IStudyTab {

    /**
     * study tab mapper
     */
    private final StudyTabMapper studyTabMapper;

    /**
     * 获取study tab分页
     * 
     * @param request
     * @return
     */
    @Override
    public ApiResponse<IPage<StudyTab>> GetStudyTabPage(StudyTabPageRequest request) {
        try {
            IPage<StudyTab> page = studyTabMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()),
                    new QueryWrapper<StudyTab>()
                            .lambda()
                            .eq(StudyTab::getIsDeleted, false)
                            // 等同于 .orderByDesc(StudyTab::getCreatedAt))
                            .orderByDesc(n -> n.getCreatedAt()));
            return ApiResponse.success(page);
        } catch (Exception e) {
            log.error(String.format("get study tab page failed, request: %s", JsonUtils.toSilentJson(request)), e);
            return ApiResponse.error("get study tab page failed");
        }
    }

    /**
     * 创建或更新study tab
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ApiResponse<Integer> CreateOrUpdateStudyTab(CreateOrUpdateStudyTabRequest request) {
        StudyTab studyTab = null;
        try {
            if (request.getId() == null || request.getId() == 0) {
                studyTab = new StudyTab();
                studyTab.setName(request.getName());
                int insertCount = studyTabMapper.insert(studyTab);
                return ApiResponse.success(insertCount);
            } else {
                studyTab = studyTabMapper.selectById(request.getId());
                if (studyTab == null) {
                    return ApiResponse.error("study tab not found");
                }
                studyTab.UpdateName(request.getName());
                int updateCount = studyTabMapper.updateById(studyTab);
                return ApiResponse.success(updateCount);
            }
        } catch (Exception e) {
            log.error(
                    String.format("create or update study tab failed, studyTab: %s", JsonUtils.toSilentJson(studyTab)),
                    e);
            return ApiResponse.error("create or update study tab failed");
        }
    }

    /**
     * 删除study tab
     * 
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ApiResponse<Integer> DeleteStudyTab(Long id) {
        try {
            int deleteCount = studyTabMapper.deleteById(id);
            return ApiResponse.success(deleteCount);
        } catch (Exception e) {
            log.error(String.format("delete study tab failed, id: %d", id), e);
            return ApiResponse.error("delete study tab failed");
        }
    }
}
