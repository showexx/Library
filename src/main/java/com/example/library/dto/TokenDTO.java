package com.example.library.dto;

import lombok.*;

@Getter
@Setter

public class TokenDTO {
    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
