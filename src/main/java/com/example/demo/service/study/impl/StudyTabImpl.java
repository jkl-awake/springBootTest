package com.example.demo.service.study.impl;

import com.example.demo.mapper.StudyArticleMapper;
import com.example.demo.model.dos.StudyArticle;
import com.example.demo.model.vo.study.StudyArticleVo;
import com.example.demo.model.vo.study.StudyCategoryVo;
import com.example.demo.model.vo.study.StudyTabVo;
import com.example.demo.model.vo.study.StudyTabWithCategoryVo;
import com.example.demo.service.study.IStudyTab;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.StudyTabMapper;
import com.example.demo.model.dos.StudyTab;
import com.example.demo.model.dto.CreateOrUpdateStudyTabDto;
import com.example.demo.model.dto.StudyTabPageDto;
import com.example.demo.common.utils.ApiResponse;
import com.example.demo.common.utils.JsonUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyTabImpl implements IStudyTab {

    private static final Long DEFAULT_CATEGORY_ID = 0L;

    /**
     * study tab mapper
     */
    private final StudyTabMapper studyTabMapper;

    /**
     * study article mapper
     */
    private final StudyArticleMapper studyArticleMapper;

    /**
     * 获取study tab分页
     * 
     */
    @Override
    public ApiResponse<IPage<StudyTab>> getStudyTabPage(StudyTabPageDto request) {
        try {
            IPage<StudyTab> page = studyTabMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()),
                    new QueryWrapper<StudyTab>()
                            .lambda()
                            .eq(StudyTab::getIsDeleted, false)
                            .orderByDesc(StudyTab::getCreatedAt));
            return ApiResponse.success(page);
        } catch (Exception e) {
            log.error(String.format("get study tab page failed, request: %s", JsonUtils.toSilentJson(request)), e);
            return ApiResponse.error("get study tab page failed");
        }
    }

    @Override
    public ApiResponse<StudyTabVo> getStudyTab(StudyTabPageDto dto) {
        try {
            // 输入验证
            if (dto == null || dto.getId() == 0) {
                return ApiResponse.error("invalid request parameter");
            }

            // 一次查询获取 tab 和所有分类信息（移除冗余的 selectById）
            List<StudyTabWithCategoryVo> studyTabWithCategoryVos =
                    studyTabMapper.getStudyTabWithCategories(dto.getId());

            // 检查 tab 是否存在
            if (studyTabWithCategoryVos == null || studyTabWithCategoryVos.isEmpty()) {
                return ApiResponse.error("study tab not found");
            }

            // 一次遍历完成所有数据提取
            StudyTabWithCategoryVo firstVo = studyTabWithCategoryVos.get(0);
            Long tabId = firstVo.getTabId();
            String tabName = firstVo.getTabName();
            Long firstCategoryId = firstVo.getCategoryId() != 0 ?
                    firstVo.getCategoryId() : DEFAULT_CATEGORY_ID;

            // 构建分类列表（只在有分类时才构建）
            List<StudyCategoryVo> categoryVoList = null;
            if (!firstCategoryId.equals(DEFAULT_CATEGORY_ID)) {
                categoryVoList = studyTabWithCategoryVos.stream()
                        .filter(vo -> vo.getCategoryId() != 0)
                        .map(vo -> StudyCategoryVo.builder()
                                .categoryId(vo.getCategoryId())
                                .categoryName(vo.getCategoryName())
                                .build())
                        .toList();
            }

            // 查询第一个分类的最新文章
            StudyArticleVo articleVo = null;
            if (!firstCategoryId.equals(DEFAULT_CATEGORY_ID)) {
                StudyArticle article = studyArticleMapper.selectOne(
                        new QueryWrapper<StudyArticle>()
                                .lambda()
                                .eq(StudyArticle::getCategoryId, firstCategoryId)
                                .eq(StudyArticle::getIsDeleted, false)
                                .orderByDesc(StudyArticle::getCreatedAt)
                                .last("limit 1"));

                if (article != null) {
                    articleVo = StudyArticleVo.builder()
                            .id(article.getId())
                            .title(article.getTitle())
                            .content(article.getContent())
                            .build();
                }
            }

            // 构建返回对象
            StudyTabVo studyTabVo = new StudyTabVo(tabId, tabName, categoryVoList, articleVo);
            return ApiResponse.success(studyTabVo);

        } catch (Exception e) {
            log.error("get study tab failed, request: {}", JsonUtils.toSilentJson(dto), e);
            return ApiResponse.error("get study tab failed");
        }
    }

    /**
     * 创建或更新study tab
     * 
     */
    @Override
    @Transactional
    public ApiResponse<Integer> createOrUpdateStudyTab(CreateOrUpdateStudyTabDto request) {
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
     * @param id tab id
     * @return int
     */
    @Override
    @Transactional
    public ApiResponse<Integer> deleteStudyTab(Long id) {
        try {
            int deleteCount = studyTabMapper.deleteById(id);
            return ApiResponse.success(deleteCount);
        } catch (Exception e) {
            log.error(String.format("delete study tab failed, id: %d", id), e);
            return ApiResponse.error("delete study tab failed");
        }
    }
}
