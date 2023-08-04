package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;


@Table(name = "book")
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "date_of_creation")
    private int dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "library")
    private Library library;

}
