package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateDto implements Serializable {
    private String name;
    private String image;
    private int star;
    private int platform;
    private String evaluation;
}
