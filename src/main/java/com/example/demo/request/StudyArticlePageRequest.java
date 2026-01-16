package com.example.demo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudyArticlePageRequest extends PageRequest {
    private Long categoryId;
}
