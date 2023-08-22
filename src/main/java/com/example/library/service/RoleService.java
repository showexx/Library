package com.example.library.service;

import com.example.library.exception.ApplicationException;
import com.example.library.model.Role;
import com.example.library.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "Роль пользователя не найдена"));
    }
}