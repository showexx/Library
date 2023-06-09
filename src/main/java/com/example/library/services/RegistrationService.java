package com.example.library.services;

import com.example.library.models.User;
import com.example.library.models.enums.Role;
import com.example.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User user) {
            user.setActive(true);
            user.setRole(Role.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
    }
}
