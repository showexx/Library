package com.example.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationPersonDTO {
    private String email;
    private String password;
    private String confirmPassword;
}
