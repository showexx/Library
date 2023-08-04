package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class RegistrationPersonDTO {
    private String email;
    private String password;
    private String confirmPassword;

    public RegistrationPersonDTO() {
    }

    public RegistrationPersonDTO(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
