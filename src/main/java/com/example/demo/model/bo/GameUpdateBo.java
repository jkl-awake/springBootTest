package com.example.demo.model.bo;

import com.example.demo.common.enums.PlatformEnum;
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
    private int star;
    private PlatformEnum platform;
    private String evaluation;
}
