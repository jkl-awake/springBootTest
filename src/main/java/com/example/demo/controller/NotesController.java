package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.utils.ApiResponse;
import com.example.demo.model.dos.Notes;
import com.example.demo.model.dto.NotesCreateOrUpdateDto;
import com.example.demo.model.dto.NotesPageDto;
import com.example.demo.service.notes.INotesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final INotesService notesService;

    /**
     * 获取笔记分页列表
     */
    @GetMapping("/page")
    public ApiResponse<IPage<Notes>> getNotesPage(
        @Valid @RequestParam NotesPageDto request
    ) {
        return notesService.GetNotesPage(request);
    }

    /**
     * 创建或更新笔记
     */
    @PostMapping("/createOrUpdate")
    public ApiResponse<Integer> createOrUpdateNotes(
        @Valid @RequestBody NotesCreateOrUpdateDto request
    ) {
        return notesService.CreateOrUpdateNotes(request);
    }

    /**
     * 删除笔记
     */
    @PostMapping("/delete")
    public ApiResponse<Integer> deleteNotes(@Valid @RequestBody Long id) {
        return notesService.DeleteNotes(id);
    }
}
