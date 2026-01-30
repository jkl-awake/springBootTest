package com.example.demo.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudyCategoryPageBo extends PageBo  implements Serializable {
    private Integer tabId;
}
