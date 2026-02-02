package com.example.demo.model.vo.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyTabWithCategoryVo {

    private long tabId;
    private String tabName;
    private long categoryId;
    private String categoryName;
}
