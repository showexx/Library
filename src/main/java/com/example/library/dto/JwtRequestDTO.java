package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class JwtRequestDTO {
    private String email;
    private String password;

    public JwtRequestDTO() {
    }

    public JwtRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
