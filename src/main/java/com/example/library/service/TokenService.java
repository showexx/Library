package com.example.library.service;

import com.example.library.dto.TokenDTO;
import com.example.library.dto.PersonDTO;
import com.example.library.exception.ApplicationException;
import com.example.library.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TokenService {
    private final PersonService personService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public TokenService(PersonService personService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.personService = personService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    public String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("Токен не найден в заголовке Authorization");
        }
    }

    public ResponseEntity<?> createAuthToken(@RequestBody PersonDTO personDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(personDTO.getEmail(), personDTO.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApplicationException(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = personService.loadUserByUsername(personDTO.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
