package com.example.library.controllers;

import com.example.library.dto.CreateBookDTO;
import com.example.library.services.BookService;
import com.example.library.services.TokenService;
import com.example.library.util.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/book")
public class BookController {
    private final BookService bookService;
    private final TokenService tokenService;
    private final JwtTokenUtils jwtTokenUtils;

    public BookController(BookService bookService, TokenService tokenService, JwtTokenUtils jwtTokenUtils) {
        this.bookService = bookService;
        this.tokenService = tokenService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping("/create-book")
    public ResponseEntity<?> createLibrary(HttpServletRequest request, @RequestBody CreateBookDTO bookRequest) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        bookService.createNewBook(jwtTokenUtils.getEmail(token), bookRequest);
        return ResponseEntity.ok("Книгу успешно добавлена");
    }
}
