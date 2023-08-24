package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.service.BookService;
import com.example.library.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/private/book")
@RestController
public class BookController {
    private final BookService bookService;
    private final TokenService tokenService;
    public BookController(BookService bookService, TokenService tokenService) {
        this.bookService = bookService;
        this.tokenService = tokenService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        bookService.createNewBook(bookDTO);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{libraryName}/{bookName}")
    public ResponseEntity<?> removeBook(HttpServletRequest request, @PathVariable String bookName, @PathVariable String libraryName) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        bookService.removeBook(token, bookName, libraryName);
        return ResponseEntity.ok("Success");
    }
}
