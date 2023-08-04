package com.example.library.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;


@ConfigurationProperties(prefix = "jwt")
@Data
@Component
public class JwtProperties {
    private String secret;
    private Duration lifetime;
}
