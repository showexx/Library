package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class JwtResponseDTO {
    private String token;

    public JwtResponseDTO() {
    }

    public JwtResponseDTO(String token) {
        this.token = token;
    }
}
