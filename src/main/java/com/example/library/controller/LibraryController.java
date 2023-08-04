package com.example.library.controller;

import com.example.library.dto.CreateLibraryDTO;
import com.example.library.service.LibraryService;
import com.example.library.service.TokenService;
import com.example.library.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/private/library")
@Data
@RestController
public class LibraryController {
    private final LibraryService libraryService;
    private final TokenService tokenService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create-library")
    public ResponseEntity<?> createLibrary(HttpServletRequest request, @RequestBody CreateLibraryDTO libraryRequest) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));

        libraryService.createNewLibrary(jwtTokenUtil.getEmail(token), libraryRequest);
        return ResponseEntity.ok("Библиотека успешно создана");
    }
}
