package com.example.demo.model.bo;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameUpdateBo implements Serializable {

    private Long id;
    private String name;
    private String image;
    private Integer star;
    private Integer platform;
    private String evaluation;
}
