package com.example.demo.service.notes;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.Notes;
import com.example.demo.request.NotesCreateOrUpdateRequest;
import com.example.demo.request.NotesPageRequest;
import com.example.demo.response.ApiResponse;

public interface INotesService {

    /**
     * 获取笔记分页列表
     */
    ApiResponse<IPage<Notes>> GetNotesPage(NotesPageRequest request);

    /**
     * 创建或更新笔记
     */
    ApiResponse<Integer> CreateOrUpdateNotes(NotesCreateOrUpdateRequest request);

    /**
     * 删除笔记
     */
    ApiResponse<Integer> DeleteNotes(Long id);
}