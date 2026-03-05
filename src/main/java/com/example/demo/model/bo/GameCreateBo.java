package com.example.demo.model.bo;

import com.example.demo.common.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateBo implements Serializable {
    private String name;
    private String image;
    private int star;
    private PlatformEnum platform;
    private String evaluation;
}
