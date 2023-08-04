package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;


@Table(name = "roles")
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
