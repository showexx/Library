package com.example.library.service;

import com.example.library.dto.LibraryDTO;
import com.example.library.exception.ApplicationException;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.LibraryRepository;
import com.example.library.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final PersonRepository personRepository;

    public LibraryService(LibraryRepository libraryRepository, PersonRepository personRepository) {
        this.libraryRepository = libraryRepository;
        this.personRepository = personRepository;
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
}
