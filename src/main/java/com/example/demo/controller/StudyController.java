package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.StudyArticle;
import com.example.demo.model.StudyCategory;
import com.example.demo.model.StudyTab;
import com.example.demo.request.CreateOrUpdateStudyArticleRequest;
import com.example.demo.request.CreateOrUpdateStudyCategoryRequest;
import com.example.demo.request.CreateOrUpdateStudyTabRequest;
import com.example.demo.request.StudyArticlePageRequest;
import com.example.demo.request.StudyCategoryPageRequest;
import com.example.demo.request.StudyTabPageRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.study.IStudyArticle;
import com.example.demo.service.study.IStudyCategory;
import com.example.demo.service.study.IStudyTab;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final IStudyArticle studyArticleService;
    private final IStudyCategory studyCategoryService;
    private final IStudyTab studyTabService;

    @GetMapping("/searchStudyArticlePage")
    public ApiResponse<IPage<StudyArticle>> GetStudyArticlePage(@Valid @RequestParam StudyArticlePageRequest request) {
        return studyArticleService.GetStudyArticlePage(request);
    }

    @PostMapping("/createOrUpdateStudyArticle")
    public ApiResponse<Integer> CreateOrUpdateStudyArticle(
            @Valid @RequestBody CreateOrUpdateStudyArticleRequest request) {
        return studyArticleService.CreateOrUpdateStudyArticle(request);
    }

    @PostMapping("/deleteStudyArticle")
    public ApiResponse<Integer> DeleteStudyArticle(@Valid @RequestParam Long id) {
        return studyArticleService.DeleteStudyArticle(id);
    }

    @GetMapping("/searchStudyCategoryPage")
    public ApiResponse<IPage<StudyCategory>> GetStudyCategoryPage(
            @Valid @RequestParam StudyCategoryPageRequest request) {
        return studyCategoryService.GetStudyCategoryPage(request);
    }

    @PostMapping("/createOrUpdateStudyCategory")
    public ApiResponse<Integer> CreateOrUpdateStudyCategory(
            @Valid @RequestBody CreateOrUpdateStudyCategoryRequest request) {
        return studyCategoryService.CreateOrUpdateStudyCategory(request);
    }

    @PostMapping("/deleteStudyCategory")
    public ApiResponse<Integer> DeleteStudyCategory(@Valid @RequestBody Long id) {
        return studyCategoryService.DeleteStudyCategory(id);
    }

    @GetMapping("/searchStudyTabPage")
    public ApiResponse<IPage<StudyTab>> GetStudyTabPage(@Valid @RequestParam StudyTabPageRequest request) {
        return studyTabService.GetStudyTabPage(request);
    }

    @PostMapping("/createOrUpdateStudyTab")
    public ApiResponse<Integer> CreateOrUpdateStudyTab(@Valid @RequestBody CreateOrUpdateStudyTabRequest request) {
        return studyTabService.CreateOrUpdateStudyTab(request);
    }

    @PostMapping("/deleteStudyTab")
    public ApiResponse<Integer> DeleteStudyTab(@Valid @RequestBody Long id) {
        return studyTabService.DeleteStudyTab(id);
    }
}
