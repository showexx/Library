package com.example.library.dto;

import com.example.library.model.Person;
import lombok.*;

@Getter
@Setter

public class LibraryDto {
    private String name;
    private Long countBooks;
    private Person owner;
}
