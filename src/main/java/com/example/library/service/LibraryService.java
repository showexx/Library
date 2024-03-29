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
    private final PersonService personService;

    public LibraryService(LibraryRepository libraryRepository, PersonRepository personRepository, JwtTokenUtils jwtTokenUtils, BookRepository bookRepository, PersonService personService) {
        this.libraryRepository = libraryRepository;
        this.personRepository = personRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.bookRepository = bookRepository;
        this.personService = personService;
    }

    public void createNewLibrary(String email, LibraryDTO libraryDto) {
        Person person = personService.findPersonByEmail(email);
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
        Person person = personService.findPersonByEmail(email);
        List<Library> libraries = libraryRepository.findByPerson(person);

        if(libraries.isEmpty()){
            throw new ApplicationException(HttpStatus.NOT_FOUND.value(), "У пользователя не найдены библиотеки");
        }

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

    public void removeLibrary(String token, String libraryName) {
        String email = jwtTokenUtils.getEmail(token);
        Person person = personService.findPersonByEmail(email);
        Library library = libraryRepository.findByNameAndPerson(libraryName, person)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND.value(), "У пользователя не найдены библиотеки"));

        if (bookRepository.findByLibrary(library).isEmpty()) {
            libraryRepository.delete(library);
        } else {
            List<Book> libraries = bookRepository.findByLibrary(library);
            for (Book book : libraries) {
                bookRepository.delete(book);
            }
            libraryRepository.delete(library);
        }
    }
}
