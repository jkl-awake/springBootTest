package com.example.demo.service.file.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import com.example.demo.service.file.IFileService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.response.ApiResponse;

@Slf4j
@Service
public class FileServiceImpl implements IFileService {

    public ApiResponse<String> uploadFile(MultipartFile file, String uploadDir) {
        try {
            // 1. 校验文件
            if (file.isEmpty()) {
                return ApiResponse.error("文件为空");
            }

            // 2. 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : "";
            String filename = UUID.randomUUID().toString() + extension;

            // 3. 确保目录存在
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            // 4. 保存文件
            Path targetPath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // 5. 生成下载链接
            return ApiResponse.success(String.format("http://localhost:8080/api/images/download/%s", filename));
        } catch (Exception e) {
            log.error(String.format("文件上传失败，文件名为：{}", file.getOriginalFilename()), e);
            return ApiResponse.error("文件上传失败");
        }
    }
}
