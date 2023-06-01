package com.example.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private int personId;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 5, max = 100, message = "The personname must be between 5 characters and 100 characters long.")
    @Column(name = "person_name")
    private String personName;

    @NotBlank(message = "Field cannot be empty")
    @Column(name = "mail")
    private String mail;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 5, max = 100, message = "The password must be between 5 characters and 100 characters long.")
    @Column(name = "password")
    private String password;

    public Person() {
    }

    public Person(int personId, String personName, String mail, String password) {
        this.personId = personId;
        this.personName = personName;
        this.mail = mail;
        this.password = password;
    }
}
