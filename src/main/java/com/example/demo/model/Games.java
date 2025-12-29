package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.enums.PlatformEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("games")
@EqualsAndHashCode(callSuper = true)
public class Games extends BaseEntity {
    private String gameName;
    private String image;
    private int star;
    private PlatformEnum platform;
    private String evaluation;
}
