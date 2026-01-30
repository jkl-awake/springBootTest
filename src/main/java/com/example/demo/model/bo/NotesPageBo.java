package com.example.demo.model.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotesPageBo implements Serializable {
    public int pageNum = 1;
    public int pageSize = 10;
}
