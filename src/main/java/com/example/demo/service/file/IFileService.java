package com.example.demo.service.file;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.utils.ApiResponse;

public interface IFileService {
    ApiResponse<String> uploadFile(MultipartFile file, String uploadDir);
}
