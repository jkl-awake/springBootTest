package com.example.demo.model.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerUpdateBo implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String userName;
}
