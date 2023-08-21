package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import com.example.library.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final LibraryRepository libraryRepository;

    public BookService(BookRepository bookRepository, PersonRepository personRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
        this.libraryRepository = libraryRepository;
    }

    public ResponseEntity<?> createNewBook(String email, BookDTO bookDTO) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти пользователя с идентификатором: " + email));
        Library library = libraryRepository.findByPersonAndAndName(person, bookDTO.getLibraryName())
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти библиотеку с идентификатором: " + person + " / " + bookDTO.getLibraryName()));
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setName(bookDTO.getName());
        book.setDateOfCreation(bookDTO.getDateOfCreation());
        book.setLibrary(library);

        bookRepository.save(book);

        return ResponseEntity.ok(bookDTO);
    }
}
