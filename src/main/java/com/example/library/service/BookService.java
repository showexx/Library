package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.exception.ApplicationException;
import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public BookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    public void createNewBook(BookDTO bookDTO) {
        Library library = libraryRepository.findByName(bookDTO.getLibraryName())
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "Библиотека не найдена"));

        Optional<Book> existingLibrary = bookRepository.findByName(bookDTO.getName());
        if (existingLibrary.isPresent()) {
            throw new ApplicationException(HttpStatus.CONFLICT.value(), "Книга с таким именем уже существует");
        }

        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setName(bookDTO.getName());
        book.setDateOfCreation(bookDTO.getDateOfCreation());
        book.setLibrary(library);
        bookRepository.save(book);
    }
}
