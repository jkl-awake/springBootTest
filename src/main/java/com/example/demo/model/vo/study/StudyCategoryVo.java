package com.example.demo.model.vo.study;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyCategoryVo implements Serializable {

    private long categoryId;
    private String categoryName;
}
