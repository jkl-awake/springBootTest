package com.example.demo.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityBeansConfig {
    @Bean
    public JwtService jwtService(JwtProperties props) {
        return new JwtService(props.getSecret(), props.getTtlSeconds());
    }

    @Bean
    public TokenStore tokenStore(StringRedisTemplate redis, JwtProperties props) {
        return new RedisTokenStore(redis, props.getRedisPrefix());
    }
}
