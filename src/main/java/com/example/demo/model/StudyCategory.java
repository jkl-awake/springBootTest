package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("study_category")
@AllArgsConstructor
@NoArgsConstructor
public class StudyCategory extends BaseEntity {
    /**
     * 页签ID
     */
    private Long tabId;

    /**
     * 分类名称
     */
    private String name;

    public void Update(String name) {
        this.name = name;
        this.setUpdatedAt(java.time.LocalDateTime.now());
    }
}
