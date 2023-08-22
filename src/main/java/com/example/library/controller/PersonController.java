package com.example.library.controller;

import com.example.library.service.PersonService;
import com.example.library.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/private/person")
@RestController
public class PersonController {
    private final TokenService tokenService;
    private final PersonService personService;

    public PersonController(TokenService tokenService, PersonService personService) {
        this.tokenService = tokenService;
        this.personService = personService;
    }

    @GetMapping("/person-libraries")
    public ResponseEntity<?> getPersonLibraries(HttpServletRequest request){
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        return ResponseEntity.ok(personService.getPersonLibraries(token));
    }

    @GetMapping("/person-info")
    public ResponseEntity<?> getPersonInfo(HttpServletRequest request) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        return ResponseEntity.ok(personService.getPersonInfo(token));
    }
}
