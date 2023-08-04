package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

public class CreateLibraryDTO {
    private String name;

    public CreateLibraryDTO() {
    }

    public CreateLibraryDTO(String name) {
        this.name = name;
    }
}
