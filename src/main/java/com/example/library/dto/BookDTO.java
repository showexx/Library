package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class BookDTO {
    private String name;
    private String author;
    private int dateOfCreation;

    public BookDTO() {
    }

    public BookDTO(String name, String author, int dateOfCreation) {
        this.name = name;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
    }
}
