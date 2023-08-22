package com.example.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Table(name = "roles")
@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
