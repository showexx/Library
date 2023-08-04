package com.example.library.controller;

import com.example.library.dto.JwtRequestDTO;
import com.example.library.dto.RegistrationPersonDTO;
import com.example.library.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/public/authentication")
@Data
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/authorization")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        return authenticationService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid RegistrationPersonDTO registrationPersonDTO) {
        return authenticationService.createNewUser(registrationPersonDTO);
    }
}
