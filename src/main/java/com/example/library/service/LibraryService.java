package com.example.library.service;

import com.example.library.dto.LibraryDTO;
import com.example.library.exception.ApplicationException;
import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import com.example.library.repository.PersonRepository;
import com.example.library.util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final PersonRepository personRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final BookRepository bookRepository;

    public LibraryService(LibraryRepository libraryRepository, PersonRepository personRepository, JwtTokenUtils jwtTokenUtils, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.personRepository = personRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.bookRepository = bookRepository;
    }

    public void createNewLibrary(String email, LibraryDTO libraryDto) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "Пользователь не найден"));

        Optional<Library> existingLibrary = libraryRepository.findByName(libraryDto.getName());
        if (existingLibrary.isPresent()) {
            throw new ApplicationException(HttpStatus.CONFLICT.value(), "Библиотека с таким именем уже существует");
        }

        Library library = new Library();
        library.setName(libraryDto.getName());
        library.setPerson(person);
        libraryRepository.save(library);
    }

    public List<LibraryDTO> getPersonLibraries(String token) {
        String email = jwtTokenUtils.getEmail(token);
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "Пользователь не найден"));
        List<Library> libraries = libraryRepository.findByPerson(person);

        List<LibraryDTO> libraryDTOList = new ArrayList<>();
        for (Library library : libraries) {
            LibraryDTO libraryDTO = new LibraryDTO();
            List<Book> books = bookRepository.findByLibrary(library);
            libraryDTO.setName(library.getName());
            libraryDTOList.add(libraryDTO);
            libraryDTO.setBookCount(books.size());
        }
        return libraryDTOList;
    }
}
