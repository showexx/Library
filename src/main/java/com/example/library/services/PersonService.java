package com.example.library.services;

import com.example.library.dto.RegistrationPersonDTO;
import com.example.library.models.Person;
import com.example.library.repositories.PersonRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", email)
        ));
        return new User(
                person.getEmail(),
                person.getPassword(),
                person.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public Person createNewUser(RegistrationPersonDTO registrationPersonDTO) {
        Person person = new Person();
        person.setEmail(registrationPersonDTO.getEmail());
        person.setPassword(passwordEncoder.encode(registrationPersonDTO.getPassword()));
        person.setRoles(List.of(roleService.getUserRole()));
        return personRepository.save(person);
    }
}
