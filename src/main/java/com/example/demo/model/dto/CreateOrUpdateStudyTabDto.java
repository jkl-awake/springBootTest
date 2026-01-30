package com.example.demo.model.dto;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateStudyTabDto implements Serializable {
    private String name;

    @Nullable
    private Long id;
}
