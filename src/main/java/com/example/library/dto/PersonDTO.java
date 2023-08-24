package com.example.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String email;
    private String role;

    public PersonDTO(String email, String role) {
        this.email = email;
        this.role = role;
    }
}
