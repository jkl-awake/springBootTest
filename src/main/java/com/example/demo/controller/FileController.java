package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.response.ApiResponse;
import com.example.demo.service.file.IFileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    
    private final IFileService fileService;

    // 上传文件
    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(MultipartFile file, String uploadDir) {
        return fileService.uploadFile(file, uploadDir);
    }
}
