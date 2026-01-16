package com.example.demo.service.study;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.StudyArticleMapper;
import com.example.demo.model.StudyArticle;
import com.example.demo.request.CreateOrUpdateStudyArticleRequest;
import com.example.demo.request.StudyArticlePageRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.JsonUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyArticleImpl implements IStudyArticle {

    private final StudyArticleMapper studyArticleMapper;

    /**
     * 获取study article分页
     * 
     * @param request
     * @return
     */
    @Override
    public ApiResponse<IPage<StudyArticle>> GetStudyArticlePage(StudyArticlePageRequest request) {
        try {
            IPage<StudyArticle> page = studyArticleMapper.selectPage(
                    new Page<>(request.getPageNum(), request.getPageSize()),
                    new QueryWrapper<StudyArticle>()
                            .lambda()
                            .eq(StudyArticle::getIsDeleted, false)
                            .eq(request.getCategoryId() != null, StudyArticle::getCategoryId, request.getCategoryId())
                            .orderByDesc(StudyArticle::getCreatedAt));
            return ApiResponse.success(page);
        } catch (Exception e) {
            log.error(String.format("get study article page failed, request: {}", JsonUtils.toSilentJson(request)), e);
            return ApiResponse.error("get study article page failed");
        }
    }

    /**
     * 创建或更新study article
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ApiResponse<Integer> CreateOrUpdateStudyArticle(CreateOrUpdateStudyArticleRequest request) {
        StudyArticle studyArticle = null;
        try {
            if (request.getId() == null || request.getId() == 0) {
                studyArticle = new StudyArticle();
                studyArticle.setTitle(request.getTitle());
                studyArticle.setContent(request.getContent());
                studyArticle.setCategoryId(request.getCategoryId());
                int insertCount = studyArticleMapper.insert(studyArticle);
                return ApiResponse.success(insertCount);
            } else {
                studyArticle = studyArticleMapper.selectById(request.getId());
                if (studyArticle == null) {
                    return ApiResponse.error("study article not found");
                }
                studyArticle.Update(request.getTitle(), request.getContent());
                int updateCount = studyArticleMapper.updateById(studyArticle);
                return ApiResponse.success(updateCount);
            }
        } catch (Exception e) {
            log.error(
                    String.format("create or update study article failed, studyArticle: {}",
                            JsonUtils.toSilentJson(studyArticle)),
                    e);
            return ApiResponse.error("create or update study article failed");
        }
    }

    /**
     * 删除study article
     * 
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ApiResponse<Integer> DeleteStudyArticle(Long id) {
        try {
            int deleteCount = studyArticleMapper.deleteById(id);
            return ApiResponse.success(deleteCount);
        } catch (Exception e) {
            log.error(String.format("delete study article failed, id: {}", id), e);
            return ApiResponse.error("delete study article failed");
        }
    }
}
