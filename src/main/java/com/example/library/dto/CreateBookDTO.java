package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class CreateBookDTO {
    private String name;
    private String author;
    private int dateOfCreation;
    private String libraryName;

    public CreateBookDTO() {
    }

    public CreateBookDTO(String name, String author, int dateOfCreation, String libraryName) {
        this.name = name;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
        this.libraryName = libraryName;
    }
}
