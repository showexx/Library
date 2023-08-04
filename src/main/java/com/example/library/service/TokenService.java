package com.example.library.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class TokenService {
    public String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("Токен не найден в заголовке Authorization");
        }
    }
}
