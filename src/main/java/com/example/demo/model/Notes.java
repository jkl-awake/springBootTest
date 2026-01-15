package com.example.demo.model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("notes")
@AllArgsConstructor
@NoArgsConstructor
public class Notes extends BaseEntity {
    private String title;
    private String content;

    public void UpdateNotes(String title,String content) {
        this.setTitle(title);
        this.setContent(content);
        this.setUpdatedAt(LocalDateTime.now());
    }
}
