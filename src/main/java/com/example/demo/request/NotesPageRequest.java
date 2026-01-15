package com.example.demo.request;

import lombok.Data;

@Data
public class NotesPageRequest {
    public int pageNum = 1;
    public int pageSize = 10;
}
