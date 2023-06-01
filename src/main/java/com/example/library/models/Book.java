package com.example.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int bookId;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 1, max = 100, message = "The name must be between 5 characters and 100 characters long.")
    @Column(name = "book_name")
    private String name;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 1, max = 100, message = "The author must be between 5 characters and 100 characters long.")
    @Column(name = "author")
    private String author;

    @NotBlank(message = "Field cannot be empty")
    @Column(name = "shelf_id")
    private int shelfId;

    public Book() {
    }

    public Book(int bookId, String name, String author, int shelfId) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.shelfId = shelfId;
    }
}
