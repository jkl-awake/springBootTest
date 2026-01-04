package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

public class JwtService {
    private final SecretKey key;
    private final long ttlSeconds;

    public JwtService(String secret, long ttlSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.ttlSeconds = ttlSeconds;
    }

    public String generateToken(Long playerId,String userName,String jti){
        Instant nowMillis = Instant.now();
        Instant expMillis = nowMillis.plusSeconds(ttlSeconds);
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(playerId.toString())
                .claim("userName", userName)
                .setId(jti)
                .setIssuedAt(Date.from(nowMillis))
                .setExpiration(Date.from(expMillis))
                .signWith(key)
                .compact();
    }

    public Claims parseClaims(String token){
        return io.jsonwebtoken.Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String newJti(){
        return java.util.UUID.randomUUID().toString();
    }
}
