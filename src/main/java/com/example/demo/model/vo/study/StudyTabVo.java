package com.example.demo.model.vo.study;

import java.io.Serializable;
import java.util.List;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyTabVo implements Serializable {

    private Long id;
    private String title;

    @Nullable
    private List<StudyCategoryVo> studyCategoryVos;

    @Nullable
    private StudyArticleVo studyArticleVo;
}
