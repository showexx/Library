package com.example.library.controllers;

import com.example.library.dto.CreateLibraryDTO;
import com.example.library.services.LibraryService;
import com.example.library.services.TokenService;
import com.example.library.util.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/library")
public class LibraryController {
    private final LibraryService libraryService;
    private final TokenService tokenService;
    private final JwtTokenUtils jwtTokenUtils;

    public LibraryController(LibraryService libraryService, TokenService tokenService, JwtTokenUtils jwtTokenUtils) {
        this.libraryService = libraryService;
        this.tokenService = tokenService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping("/create-library")
    public ResponseEntity<?> createLibrary(HttpServletRequest request, @RequestBody CreateLibraryDTO libraryRequest) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));

        libraryService.createNewLibrary(jwtTokenUtils.getEmail(token), libraryRequest);
        return ResponseEntity.ok("Библиотека успешно создана");
    }
}
