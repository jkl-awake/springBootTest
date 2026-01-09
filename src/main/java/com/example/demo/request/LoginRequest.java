package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    public String userName;

    @NotBlank
    public String password;
}
