package com.example.library.dto;

import com.example.library.models.Person;

public class LibraryDto {
    private String name;
    private Long countBooks;
    private Person owner;

    public LibraryDto(String name, Long countBooks, Person owner) {
        this.name = name;
        this.countBooks = countBooks;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(Long countBooks) {
        this.countBooks = countBooks;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
