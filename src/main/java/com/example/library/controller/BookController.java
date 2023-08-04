package com.example.library.controller;

import com.example.library.dto.CreateBookDTO;
import com.example.library.service.BookService;
import com.example.library.service.TokenService;
import com.example.library.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/private/book")
@Data
@RestController
public class BookController {
    private final BookService bookService;
    private final TokenService tokenService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create-book")
    public ResponseEntity<?> createLibrary(HttpServletRequest request, @RequestBody CreateBookDTO bookRequest) {
        String token = tokenService.extractTokenFromHeader(request.getHeader("Authorization"));
        bookService.createNewBook(jwtTokenUtil.getEmail(token), bookRequest);
        return ResponseEntity.ok("Книга успешно добавлена");
    }
}
