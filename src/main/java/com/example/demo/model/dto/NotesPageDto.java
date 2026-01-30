package com.example.demo.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotesPageDto implements Serializable {
    public int pageNum = 1;
    public int pageSize = 10;
}
