package com.example.demo.model.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("study_article")
@AllArgsConstructor
@NoArgsConstructor
public class StudyArticle extends BaseEntity {
    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    public void Update(String title, String content) {
        this.title = title;
        this.content = content;
        this.setUpdatedAt(LocalDateTime.now());
    }
}
