package com.example.library.service;

import com.example.library.dto.LibraryDTO;
import com.example.library.dto.PersonDTO;
import com.example.library.dto.RegistrationPersonDTO;
import com.example.library.exception.ApplicationException;
import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.model.Person;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import com.example.library.repository.PersonRepository;
import com.example.library.util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtTokenUtils jwtTokenUtils;
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder, RoleService roleService, JwtTokenUtils jwtTokenUtils, LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", email)));

        return new User(person.getEmail(), person.getPassword(), person.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()));
    }

    public void createNewUser(RegistrationPersonDTO registrationPersonDTO) {
        if (!registrationPersonDTO.getPassword().equals(registrationPersonDTO.getConfirmPassword())) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают");
        }
        if (findByEmail(registrationPersonDTO.getEmail()).isPresent()) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует");
        }

        Person person = new Person();
        person.setEmail(registrationPersonDTO.getEmail());
        person.setPassword(passwordEncoder.encode(registrationPersonDTO.getPassword()));
        person.setRoles(Collections.singletonList(roleService.getUserRole()));

        personRepository.save(person);
    }

    public PersonDTO getPersonInfo(String token) {
        String email = jwtTokenUtils.getEmail(token);
        String roleName = roleService.getUserRole().getName();
        return new PersonDTO(email,roleName);
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
