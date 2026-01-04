package com.example.demo.security;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Optional;

@AllArgsConstructor
public class RedisTokenStore implements TokenStore {
    private final StringRedisTemplate redis;
    private final String prefix;

    private String key(String jti) {
        return prefix + jti;
    }

    @Override
    public void store(String jti, Long playerId, Duration ttl) {
        redis.opsForValue().set(key(jti), String.valueOf(playerId), ttl);
    }

    @Override
    public boolean exists(String jti) {
        Boolean hasKey = redis.hasKey(key(jti));
        return Boolean.TRUE.equals(hasKey);
    }

    @Override
    public Optional<Long> getPlayerId(String jti) {
        String v = redis.opsForValue().get(key(jti));
        if (v == null || v.isBlank()) return Optional.empty();
        try {
            return Optional.of(Long.parseLong(v));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public void revoke(String jti) {
        redis.delete(key(jti));
    }
}
