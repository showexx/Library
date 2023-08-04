package com.example.library.service;

import com.example.library.dto.CreateBookDTO;
import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import com.example.library.repository.PersonRepository;
import lombok.Data;
import org.springframework.stereotype.Service;


@Data
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final LibraryRepository libraryRepository;

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
