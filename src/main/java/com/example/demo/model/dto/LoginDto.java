package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class LoginDto implements Serializable {

    @NotBlank
    public String userName;

    @NotBlank
    public String password;
}
