package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Users;
import com.example.demo.utils.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name="Auth", description="Authentication APIs")
public class AuthController {

    @PostMapping("/login")
    public ApiResponse<String> Login(@RequestBody Users user) {
        // 这里省略了用户验证的逻辑，假设用户已经通过验证
        String token = JwtUtil.generateToken(user.getUserName());
        if(token.isEmpty()){
            return ApiResponse.error(401, "Authentication failed");
        } else {
            return ApiResponse.success(token, "Authentication successful");
        }
    }
}
