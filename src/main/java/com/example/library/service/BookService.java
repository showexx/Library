package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.exception.ApplicationException;
import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import com.example.library.util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PersonService personService;
    private final LibraryService libraryService;

    public BookService(BookRepository bookRepository, LibraryRepository libraryRepository, JwtTokenUtils jwtTokenUtils, PersonService personService, LibraryService libraryService) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.personService = personService;
        this.libraryService = libraryService;
    }

    public void createNewBook(BookDTO bookDTO) {
        Library library = libraryRepository.findByName(bookDTO.getLibraryName())
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "У пользователя не найдены библиотеки"));

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

    public List<BookDTO> getPersonBooks(String token) {
        String email = jwtTokenUtils.getEmail(token);
        Person person = personService.findPersonByEmail(email);

        List<Library> libraries = libraryRepository.findByPerson(person);
        if (libraries.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_FOUND.value(), "У пользователя не найдены библиотеки");
        }
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Library library : libraries) {
            List<Book> books = bookRepository.findByLibrary(library);
            if (books.isEmpty()) {
                throw new ApplicationException(HttpStatus.NOT_FOUND.value(), "У пользователя не найдены книги");
            }
            for (Book book : books) {
                bookDTOS.add(new BookDTO(book.getName(), book.getAuthor(), book.getDateOfCreation(), book.getLibrary().getName()));
            }
        }

        return bookDTOS;
    }

    public void removeBook(String token, String bookName, String libraryName) {
        String email = jwtTokenUtils.getEmail(token);
        Person person = personService.findPersonByEmail(email);

        Library library = libraryRepository.findByNameAndPerson(libraryName, person)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "У пользователя не найдены библиотека"));
        Book book = bookRepository.findByNameAndLibrary(bookName, library)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "У пользователя не найдена книга"));
        bookRepository.delete(book);
    }
}
