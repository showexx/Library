package com.example.library.dto;

public class BookDTO {
    private String name;
    private String author;
    private int dateOfCreation;

    public BookDTO(String name, String author, int dateOfCreation) {
        this.name = name;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
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
