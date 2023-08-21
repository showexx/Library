package com.example.library.service;

import com.example.library.dto.LibraryDto;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.LibraryRepository;
import com.example.library.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final PersonRepository personRepository;

    public LibraryService(LibraryRepository libraryRepository, PersonRepository personRepository) {
        this.libraryRepository = libraryRepository;
        this.personRepository = personRepository;
    }

    public ResponseEntity<?> createNewLibrary(String email, LibraryDto libraryDto) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти пользователя с идентификатором: " + email));

        Library library = new Library();
        library.setName(libraryDto.getName());
        library.setPerson(person);
        libraryRepository.save(library);

        return ResponseEntity.ok(libraryDto);
    }
}
