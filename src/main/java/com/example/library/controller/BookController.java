package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.service.BookService;
import com.example.library.service.TokenService;
import com.example.library.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/private/book")
@RestController
public class BookController {
    private final BookService bookService;
    private final TokenService tokenService;
    private final JwtTokenUtil jwtTokenUtil;

    public BookController(BookService bookService, TokenService tokenService, JwtTokenUtil jwtTokenUtil) {
        this.bookService = bookService;
        this.tokenService = tokenService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/new-book")
    public ResponseEntity<?> createBook(HttpServletRequest request, @RequestBody BookDTO bookDTO) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        return bookService.createNewBook(jwtTokenUtil.getEmail(token), bookDTO);
    }
}
