package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.utils.ApiResponse;
import com.example.demo.converter.StudyConverter;
import com.example.demo.model.bo.*;
import com.example.demo.model.dos.StudyArticle;
import com.example.demo.model.dos.StudyCategory;
import com.example.demo.model.dos.StudyTab;
import com.example.demo.model.dto.CreateOrUpdateStudyArticleDto;
import com.example.demo.model.dto.CreateOrUpdateStudyCategoryDto;
import com.example.demo.model.dto.CreateOrUpdateStudyTabDto;
import com.example.demo.model.dto.StudyArticlePageDto;
import com.example.demo.model.dto.StudyCategoryPageDto;
import com.example.demo.model.dto.StudyTabPageDto;
import com.example.demo.model.vo.study.StudyTabVo;
import com.example.demo.service.study.IStudyArticle;
import com.example.demo.service.study.IStudyCategory;
import com.example.demo.service.study.IStudyTab;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final IStudyArticle studyArticleService;
    private final IStudyCategory studyCategoryService;
    private final IStudyTab studyTabService;

    private final StudyConverter studyConverter;

    @GetMapping("/searchStudyArticlePage")
    public ApiResponse<IPage<StudyArticle>> getStudyArticlePage(
        @Valid @RequestParam StudyArticlePageDto request
    ) {
        StudyArticlePageBo bo = studyConverter.toBo(request);
        return studyArticleService.GetStudyArticlePage(bo);
    }

    @PostMapping("/createOrUpdateStudyArticle")
    public ApiResponse<Integer> createOrUpdateStudyArticle(
        @Valid @RequestBody CreateOrUpdateStudyArticleDto request
    ) {
        CreateOrUpdateStudyArticleBo bo = studyConverter.toBo(request);
        return studyArticleService.CreateOrUpdateStudyArticle(bo);
    }

    @PostMapping("/deleteStudyArticle")
    public ApiResponse<Integer> deleteStudyArticle(
        @Valid @RequestParam Long id
    ) {
        return studyArticleService.DeleteStudyArticle(id);
    }

    @GetMapping("/searchStudyCategoryPage")
    public ApiResponse<IPage<StudyCategory>> getStudyCategoryPage(
        @Valid @RequestParam StudyCategoryPageDto request
    ) {
        StudyCategoryPageBo bo = studyConverter.toBo(request);
        return studyCategoryService.GetStudyCategoryPage(bo);
    }

    @PostMapping("/createOrUpdateStudyCategory")
    public ApiResponse<Integer> createOrUpdateStudyCategory(
        @Valid @RequestBody CreateOrUpdateStudyCategoryDto request
    ) {
        CreateOrUpdateStudyCategoryBo bo = studyConverter.toBo(request);
        return studyCategoryService.CreateOrUpdateStudyCategory(bo);
    }

    @PostMapping("/deleteStudyCategory")
    public ApiResponse<Integer> deleteStudyCategory(
        @Valid @RequestBody Long id
    ) {
        return studyCategoryService.DeleteStudyCategory(id);
    }

    @GetMapping("/searchStudyTabPage")
    public ApiResponse<IPage<StudyTab>> getStudyTabPage(
        @Valid @RequestParam StudyTabPageDto request
    ) {
        StudyTabPageBo bo = studyConverter.toBo(request);
        return studyTabService.getStudyTabPage(bo);
    }

    @PostMapping("/createOrUpdateStudyTab")
    public ApiResponse<Integer> createOrUpdateStudyTab(
        @Valid @RequestBody CreateOrUpdateStudyTabDto dto
    ) {
        CreateOrUpdateStudyTabBo bo = studyConverter.toBo(dto);
        return studyTabService.createOrUpdateStudyTab(bo);
    }

    @PostMapping("/deleteStudyTab")
    public ApiResponse<Integer> deleteStudyTab(@Valid @RequestBody Long id) {
        return studyTabService.deleteStudyTab(id);
    }

    /**
     * 获取study tab及其下的分类和文章
     *
     * @param dto 请求参数
     * @return StudyTabVo
     */
    @PostMapping("/getStudyTab")
    public ApiResponse<StudyTabVo> getStudyTab(@Valid @RequestBody StudyTabPageDto dto) {
        StudyTabPageBo bo = studyConverter.toBo(dto);
        return studyTabService.getStudyTab(bo);
    }
}
