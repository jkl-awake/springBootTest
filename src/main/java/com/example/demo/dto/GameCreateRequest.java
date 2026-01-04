package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateRequest {
    private String name;
    private String image;
    private int star;
    private int platform;
    private String evaluation;
}
