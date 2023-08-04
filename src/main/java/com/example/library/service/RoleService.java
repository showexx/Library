package com.example.library.service;

import com.example.library.model.Role;
import com.example.library.repository.RoleRepository;
import lombok.Data;
import org.springframework.stereotype.Service;


@Data
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
