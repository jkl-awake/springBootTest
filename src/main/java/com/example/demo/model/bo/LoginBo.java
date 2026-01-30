package com.example.demo.model.bo;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class LoginBo implements Serializable {

    @NotBlank
    public String userName;

    @NotBlank
    public String password;
}
