package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {
    private Long id;
    private String userName;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isDeleted;
}
