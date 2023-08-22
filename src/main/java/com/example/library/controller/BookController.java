package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.service.BookService;
import com.example.library.service.TokenService;
import com.example.library.util.JwtTokenUtils;
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

    public BookController(BookService bookService, TokenService tokenService) {
        this.bookService = bookService;
        this.tokenService = tokenService;
    }

    @PostMapping("/new-book")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        bookService.createNewBook(bookDTO);
        return ResponseEntity.ok("Success");
    }
}
