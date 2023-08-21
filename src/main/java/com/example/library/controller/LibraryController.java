package com.example.library.controller;

import com.example.library.dto.LibraryDto;
import com.example.library.service.LibraryService;
import com.example.library.service.TokenService;
import com.example.library.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/private/library")
@RestController
public class LibraryController {
    private final LibraryService libraryService;
    private final TokenService tokenService;
    private final JwtTokenUtil jwtTokenUtil;

    public LibraryController(LibraryService libraryService, TokenService tokenService, JwtTokenUtil jwtTokenUtil) {
        this.libraryService = libraryService;
        this.tokenService = tokenService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/new-library")
    public ResponseEntity<?> createLibrary(HttpServletRequest request, @RequestBody LibraryDto libraryDto) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));


        return libraryService.createNewLibrary(jwtTokenUtil.getEmail(token), libraryDto);
    }
}
