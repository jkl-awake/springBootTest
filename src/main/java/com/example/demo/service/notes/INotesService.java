package com.example.demo.service.notes;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.dos.Notes;
import com.example.demo.model.dto.NotesCreateOrUpdateDto;
import com.example.demo.model.dto.NotesPageDto;
import com.example.demo.response.ApiResponse;

public interface INotesService {

    /**
     * 获取笔记分页列表
     */
    ApiResponse<IPage<Notes>> GetNotesPage(NotesPageDto request);

    /**
     * 创建或更新笔记
     */
    ApiResponse<Integer> CreateOrUpdateNotes(NotesCreateOrUpdateDto request);

    /**
     * 删除笔记
     */
    ApiResponse<Integer> DeleteNotes(Long id);
}