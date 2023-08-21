package com.example.library.controller;

import com.example.library.dto.PersonDTO;
import com.example.library.dto.RegistrationPersonDTO;
import com.example.library.service.PersonService;
import com.example.library.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/public/authentication")
@RestController
public class AuthenticationController {
    private final TokenService tokenService;
    private final PersonService personService;

    public AuthenticationController(TokenService tokenService, PersonService personService) {
        this.tokenService = tokenService;
        this.personService = personService;
    }

    @PostMapping("/authorization")
    public ResponseEntity<?> createAuthToken(@RequestBody PersonDTO personDTO) {
        return tokenService.createAuthToken(personDTO);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid RegistrationPersonDTO registrationPersonDTO) {
        return personService.createNewUser(registrationPersonDTO);
    }
}
