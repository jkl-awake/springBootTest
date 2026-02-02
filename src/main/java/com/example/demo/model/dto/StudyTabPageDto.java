package com.example.demo.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudyTabPageDto extends PageDto implements Serializable {

    private long id;
}
