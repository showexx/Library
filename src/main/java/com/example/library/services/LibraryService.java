package com.example.library.services;

import com.example.library.dto.CreateLibraryDTO;
import com.example.library.models.Library;
import com.example.library.models.Person;
import com.example.library.repositories.LibraryRepository;
import com.example.library.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final PersonRepository personRepository;

    public LibraryService(LibraryRepository libraryRepository, PersonRepository personRepository) {
        this.libraryRepository = libraryRepository;
        this.personRepository = personRepository;
    }

    public void createNewLibrary(String email, CreateLibraryDTO createLibraryDTO) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти пользователя с идентификатором: " + email));

        Library library = new Library();
        library.setName(createLibraryDTO.getName());
        library.setPerson(person);
        libraryRepository.save(library);
    }
}
