package com.example.library.service;

import com.example.library.dto.JwtRequestDTO;
import com.example.library.dto.JwtResponseDTO;
import com.example.library.dto.PersonDTO;
import com.example.library.dto.RegistrationPersonDTO;
import com.example.library.model.Person;
import com.example.library.exception.ApplicationException;
import com.example.library.util.JwtTokenUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Data
@Service
public class AuthenticationService {
    private final PersonService personService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApplicationException(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationPersonDTO registrationPersonDTO) {
        if (!registrationPersonDTO.getPassword().equals(registrationPersonDTO.getConfirmPassword())) {
            return new ResponseEntity<>(new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (personService.findByEmail(registrationPersonDTO.getEmail()).isPresent()) {
            return new ResponseEntity<>(new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        Person person = personService.createNewUser(registrationPersonDTO);
        return ResponseEntity.ok(new PersonDTO(person.getId(), person.getEmail()));
    }
}
