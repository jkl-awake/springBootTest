package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.dos.StudyArticle;
import com.example.demo.model.dos.StudyCategory;
import com.example.demo.model.dos.StudyTab;
import com.example.demo.model.dto.CreateOrUpdateStudyArticleDto;
import com.example.demo.model.dto.CreateOrUpdateStudyCategoryDto;
import com.example.demo.model.dto.CreateOrUpdateStudyTabDto;
import com.example.demo.model.dto.StudyArticlePageDto;
import com.example.demo.model.dto.StudyCategoryPageDto;
import com.example.demo.model.dto.StudyTabPageDto;
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
    public ApiResponse<IPage<StudyArticle>> GetStudyArticlePage(@Valid @RequestParam StudyArticlePageDto request) {
        return studyArticleService.GetStudyArticlePage(request);
    }

    @PostMapping("/createOrUpdateStudyArticle")
    public ApiResponse<Integer> CreateOrUpdateStudyArticle(
            @Valid @RequestBody CreateOrUpdateStudyArticleDto request) {
        return studyArticleService.CreateOrUpdateStudyArticle(request);
    }

    @PostMapping("/deleteStudyArticle")
    public ApiResponse<Integer> DeleteStudyArticle(@Valid @RequestParam Long id) {
        return studyArticleService.DeleteStudyArticle(id);
    }

    @GetMapping("/searchStudyCategoryPage")
    public ApiResponse<IPage<StudyCategory>> GetStudyCategoryPage(
            @Valid @RequestParam StudyCategoryPageDto request) {
        return studyCategoryService.GetStudyCategoryPage(request);
    }

    @PostMapping("/createOrUpdateStudyCategory")
    public ApiResponse<Integer> CreateOrUpdateStudyCategory(
            @Valid @RequestBody CreateOrUpdateStudyCategoryDto request) {
        return studyCategoryService.CreateOrUpdateStudyCategory(request);
    }

    @PostMapping("/deleteStudyCategory")
    public ApiResponse<Integer> DeleteStudyCategory(@Valid @RequestBody Long id) {
        return studyCategoryService.DeleteStudyCategory(id);
    }

    @GetMapping("/searchStudyTabPage")
    public ApiResponse<IPage<StudyTab>> GetStudyTabPage(@Valid @RequestParam StudyTabPageDto request) {
        return studyTabService.GetStudyTabPage(request);
    }

    @PostMapping("/createOrUpdateStudyTab")
    public ApiResponse<Integer> CreateOrUpdateStudyTab(@Valid @RequestBody CreateOrUpdateStudyTabDto request) {
        return studyTabService.CreateOrUpdateStudyTab(request);
    }

    @PostMapping("/deleteStudyTab")
    public ApiResponse<Integer> DeleteStudyTab(@Valid @RequestBody Long id) {
        return studyTabService.DeleteStudyTab(id);
    }
}
