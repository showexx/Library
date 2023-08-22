package com.example.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String name;
    private String author;
    private int dateOfCreation;
    private String libraryName;

    public BookDTO(String name, String author, int dateOfCreation, String libraryName) {
        this.name = name;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
        this.libraryName = libraryName;
    }
}
