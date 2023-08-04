package com.example.library.service;

import com.example.library.dto.CreateLibraryDTO;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.LibraryRepository;
import com.example.library.repository.PersonRepository;
import lombok.Data;
import org.springframework.stereotype.Service;


@Data
@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final PersonRepository personRepository;

    public void createNewLibrary(String email, CreateLibraryDTO createLibraryDTO) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти пользователя с идентификатором: " + email));

        Library library = new Library();
        library.setName(createLibraryDTO.getName());
        library.setPerson(person);
        libraryRepository.save(library);
    }
}
