package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.Notes;
import com.example.demo.request.NotesCreateOrUpdateRequest;
import com.example.demo.request.NotesPageRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.notes.INotesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final INotesService notesService;

    /**
     * 获取笔记分页列表
     */
    @GetMapping("/page")
    public ApiResponse<IPage<Notes>> GetNotesPage(@Valid @RequestParam NotesPageRequest request) {
        return notesService.GetNotesPage(request);
    }

    /**
     * 创建或更新笔记
     */
    @PostMapping("/createOrUpdate")
    public ApiResponse<Integer> CreateOrUpdateNotes(@Valid @RequestBody NotesCreateOrUpdateRequest request) {
        return notesService.CreateOrUpdateNotes(request);
    }

    /**
     * 删除笔记
     */
    @PostMapping("/delete")
    public ApiResponse<Integer> DeleteNotes(@Valid @RequestBody Long id) {
        return notesService.DeleteNotes(id);
    }
}
