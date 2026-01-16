package com.example.demo.request;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateStudyArticleRequest {
    private String title;

    private String content;

    private Long categoryId;

    @Nullable
    private Long id;
}
