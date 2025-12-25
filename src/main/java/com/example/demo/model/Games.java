package com.example.demo.model;


import com.example.demo.enums.PlatformEnum;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "games", schema = "player")
@EqualsAndHashCode(callSuper = true)
public class Games extends BaseEntity {
    @Column(name = "game_name")
    private String GameName;

    @Column(name = "image")
    private String Image;

    @Column(name = "star")
    private int Star;

    @Column(name = "platform")
    @Enumerated(EnumType.ORDINAL)
    private PlatformEnum Platform;

    @Column(name = "evaluation")
    private String Evaluation;

}
