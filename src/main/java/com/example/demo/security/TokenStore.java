package com.example.demo.security;

import java.time.Duration;
import java.util.Optional;

public interface TokenStore {
    void store(String jti, Long playerId, Duration ttl);
    boolean exists(String jti);
    Optional<Long> getPlayerId(String jti);
    void revoke(String jti);
}
