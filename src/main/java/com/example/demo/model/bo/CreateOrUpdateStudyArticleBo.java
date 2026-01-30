package com.example.demo.model.bo;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateStudyArticleBo implements Serializable {
    private String title;

    private String content;

    private Long categoryId;

    @Nullable
    private Long id;
}
