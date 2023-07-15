package com.example.library.dto;

public class CreateBookDTO {
    private String name;
    private String author;
    private int dateOfCreation;
    private String libraryName;

    public CreateBookDTO(String name, String author, int dateOfCreation, String libraryName) {
        this.name = name;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
        this.libraryName = libraryName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(int dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
