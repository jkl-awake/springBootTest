package com.example.demo.model.vo.study;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyArticleVo implements Serializable {

    private long id;
    private String title;
    private String content;
}
