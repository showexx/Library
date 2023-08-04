package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;


@Table(name = "library")
@Data
@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "person")
    private Person person;
}
