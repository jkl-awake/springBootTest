package com.example.demo.request;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateStudyCategoryRequest {
    private String name;

    private Long tabId;

    @Nullable
    private Long id;
}
