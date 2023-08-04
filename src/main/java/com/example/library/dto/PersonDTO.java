package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class PersonDTO {
    private int id;
    private String email;

    public PersonDTO() {
    }

    public PersonDTO(int id, String email) {
        this.id = id;
        this.email = email;
    }
}
