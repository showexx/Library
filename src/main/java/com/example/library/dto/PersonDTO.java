package com.example.library.dto;

import lombok.*;

@Getter
@Setter

public class PersonDTO {
    private String email;
    private String password;

    public PersonDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
