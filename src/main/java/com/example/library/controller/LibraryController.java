package com.example.library.controller;

import com.example.library.dto.LibraryDTO;
import com.example.library.service.LibraryService;
import com.example.library.service.TokenService;
import com.example.library.util.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/private/library")
@RestController
public class LibraryController {
    private final LibraryService libraryService;
    private final TokenService tokenService;
    private final JwtTokenUtils jwtTokenUtils;

    public LibraryController(LibraryService libraryService, TokenService tokenService, JwtTokenUtils jwtTokenUtils) {
        this.libraryService = libraryService;
        this.tokenService = tokenService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createLibrary(HttpServletRequest request, @RequestBody LibraryDTO libraryDto) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        libraryService.createNewLibrary(jwtTokenUtils.getEmail(token), libraryDto);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{libraryName}")
    public ResponseEntity<?> removeLibrary(HttpServletRequest request, @PathVariable String libraryName) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        libraryService.removeLibrary(token, libraryName);
        return ResponseEntity.ok("Success");
    }
}
