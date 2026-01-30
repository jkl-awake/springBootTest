package com.example.demo.model.dos;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("study_tab")
@AllArgsConstructor
@NoArgsConstructor
public class StudyTab extends BaseEntity {
    /**
     * 页签名称
     */
    private String name;

    public void UpdateName(String name) {
        this.name = name;
        this.setUpdatedAt(LocalDateTime.now());
    }
}
