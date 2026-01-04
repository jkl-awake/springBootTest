package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Players;
import com.example.demo.security.JwtProperties;
import com.example.demo.security.JwtService;
import com.example.demo.security.TokenStore;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name="Auth", description="Authentication APIs")
public class AuthController {
    private final JwtService jwtService;
    private final TokenStore tokenStore;
    private final JwtProperties properties;

//    @PostMapping("/login")
//    public ApiResponse<String> Login(@RequestBody Users user) {
//        // 这里省略了用户验证的逻辑，假设用户已经通过验证
//        String token = JwtUtil.generateToken(user.getUserName());
//        if(token.isEmpty()){
//            return ApiResponse.error(401, "Authentication failed");
//        } else {
//            return ApiResponse.success(token, "Authentication successful");
//        }
//    }

    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@RequestBody LoginRequest req) {
        // TODO: 用你的 player 表验证账号密码，拿到 Players
        // Players player = playerService.verify(req.userName, req.password);
        Players player = new Players(); // 占位：请替换
        player.setId(1L);
        player.setUserName(req.userName);

        String jti = JwtService.newJti();
        String token = jwtService.generateToken(player.getId(), player.getUserName(), jti);

        tokenStore.store(jti, player.getId(), Duration.ofSeconds(properties.getTtlSeconds()));

        return ApiResponse.success(Map.of("token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring("Bearer ".length()).trim();
            try {
                String jti = jwtService.parseClaims(token).getId();
                if (jti != null && !jti.isBlank()) {
                    tokenStore.revoke(jti);
                }
            } catch (Exception ignored) {
                // ignore
            }
        }
        return ResponseEntity.ok(ApiResponse.success(null, "logged out"));
    }
}
