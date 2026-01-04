package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.enums.PlatformEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("games")
@EqualsAndHashCode(callSuper = true)
public class Games extends BaseEntity {

    @TableField("game_name")
    private String gameName;

    private String image;
    private int star;
    private PlatformEnum platform;
    private String evaluation;

    public Games(String gameName, String image, int star, int platform, String evaluation) {
        this.gameName = gameName;
        this.image = image;
        this.star = star;
        this.platform = PlatformEnum.fromCode(platform);
        this.evaluation = evaluation;
    }

    public void UpdateGame(String gameName, String image, int star, PlatformEnum platform, String evaluation) {
        this.gameName = gameName;
        this.image = image;
        this.star = star;
        this.platform = platform;
        this.evaluation = evaluation;
    }
}
