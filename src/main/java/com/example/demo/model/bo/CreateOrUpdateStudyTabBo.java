package com.example.demo.model.bo;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateStudyTabBo implements Serializable {
    private String name;

    @Nullable
    private Long id;
}
