package com.example.library.dto;

import com.example.library.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class LibraryDto {
    private String name;
    private Long countBooks;
    private Person owner;

    public LibraryDto() {
    }

    public LibraryDto(String name, Long countBooks, Person owner) {
        this.name = name;
        this.countBooks = countBooks;
        this.owner = owner;
    }
}
