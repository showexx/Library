package com.example.library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    private int libraryId;

    @NotBlank(message = "Field cannot be empty")
    @Column(name = "owner_id")
    private int ownerId;

    @NotBlank(message = "Field cannot be empty")
    @Column(name = "book_id")
    private int bookId;
}
