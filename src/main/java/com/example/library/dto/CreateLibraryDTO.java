package com.example.library.dto;

public class CreateLibraryDTO {
    private String name;

    public CreateLibraryDTO() {
    }

    public CreateLibraryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
