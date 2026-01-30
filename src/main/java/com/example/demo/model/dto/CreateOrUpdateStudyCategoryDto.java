package com.example.demo.model.dto;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateStudyCategoryDto implements Serializable {
    private String name;

    private Long tabId;

    @Nullable
    private Long id;
}
