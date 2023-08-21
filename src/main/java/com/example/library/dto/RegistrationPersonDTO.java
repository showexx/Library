package com.example.library.dto;

import lombok.*;

@Getter
@Setter

public class RegistrationPersonDTO {
    private String email;
    private String password;
    private String confirmPassword;
}
