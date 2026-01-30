package com.example.demo.service.notes.impl;

import com.example.demo.service.notes.INotesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.NotesMapper;
import com.example.demo.model.dos.Notes;
import com.example.demo.model.dto.NotesCreateOrUpdateDto;
import com.example.demo.model.dto.NotesPageDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.common.utils.JsonUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotesServiceImpl implements INotesService {

    private final NotesMapper notesMapper;

    /**
     * 创建或更新笔记
     */
    @Transactional
    public ApiResponse<Integer> CreateOrUpdateNotes(NotesCreateOrUpdateDto request) {
        try {
            if (request.getId() != null && request.getId() > 0) {
                Notes notes = notesMapper.selectById(request.getId());
                notes.UpdateNotes(request.getTitle(), request.getContent());
                notesMapper.updateById(notes);
                log.info("update note success");
            } else {
                Notes notes = new Notes(request.getTitle(), request.getContent());
                notesMapper.insert(notes);
                log.info("create note success");
            }
            return ApiResponse.success(1);
        } catch (Exception e) {
            String requestJson = JsonUtils.toSilentJson(request);
            log.error(String.format("create or update note failed, request: {}", requestJson, e));
            return ApiResponse.error("create or update note failed");
        }
    }

    /**
     * 删除笔记
     */
    @Transactional
    public ApiResponse<Integer> DeleteNotes(Long id) {
        try {
            Notes notes = notesMapper.selectById(id);
            if (notes == null) {
                return ApiResponse.error("note not found");
            }
            notesMapper.deleteById(notes);
            log.info("delete note success");
            return ApiResponse.success(1);
        } catch (Exception e) {
            log.error(String.format("delete note failed, id: {}", id), e);
            return ApiResponse.error("delete note failed");
        }
    }

    /**
     * 获取笔记分页列表
     */
    public ApiResponse<IPage<Notes>> GetNotesPage(NotesPageDto request) {
        try {
            IPage<Notes> page = notesMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()),
                    new QueryWrapper<Notes>()
                            .lambda()
                            .eq(Notes::getIsDeleted, false)
                            // 等同于 .orderByDesc(Notes::getCreatedAt))
                            .orderByDesc(n -> n.getCreatedAt()));
            return ApiResponse.success(page);
        } catch (Exception e) {
            log.error(String.format("get notes page failed, request: {}", JsonUtils.toSilentJson(request)), e);
            return ApiResponse.error("get notes page failed");
        }
    }
}
