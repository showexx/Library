package com.example.library.services;

import com.example.library.dto.CreateBookDTO;
import com.example.library.models.Book;
import com.example.library.models.Library;
import com.example.library.models.Person;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.LibraryRepository;
import com.example.library.repositories.PersonRepository;
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

    public void createNewBook(String email,CreateBookDTO createBookDTO) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти пользователя с идентификатором: " + email));
        Library library = libraryRepository.findByPersonAndAndName(person, createBookDTO.getLibraryName())
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти библиотеку с идентификатором: " + person + " / " + createBookDTO.getLibraryName()));
        Book book = new Book();
        book.setAuthor(createBookDTO.getAuthor());
        book.setName(createBookDTO.getName());
        book.setDateOfCreation(createBookDTO.getDateOfCreation());
        book.setLibrary(library);

        bookRepository.save(book);
    }
}
