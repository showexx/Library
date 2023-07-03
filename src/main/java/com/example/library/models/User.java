package com.example.library.models;

import com.example.library.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @NotBlank(message = "Field cannot be empty")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 5, max = 100, message = "The password must be between 5 characters and 100 characters long.")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Field cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Field cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "active")
    private boolean active;


}
