package com.example.library.services;

import com.example.library.dto.JwtRequestDTO;
import com.example.library.dto.JwtResponseDTO;
import com.example.library.dto.PersonDTO;
import com.example.library.dto.RegistrationPersonDTO;
import com.example.library.models.Person;
import com.example.library.util.ApplicationError;
import com.example.library.util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticationService {
    private final PersonService personService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PersonService personService, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager) {
        this.personService = personService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationPersonDTO registrationPersonDTO) {
        if (!registrationPersonDTO.getPassword().equals(registrationPersonDTO.getConfirmPassword())) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (personService.findByEmail(registrationPersonDTO.getEmail()).isPresent()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        Person person = personService.createNewUser(registrationPersonDTO);
        return ResponseEntity.ok(new PersonDTO(person.getId(), person.getEmail()));
    }
}
