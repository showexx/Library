package com.example.library.repository;

import com.example.library.model.Library;
import com.example.library.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Integer> {
    Optional<Library> findByName(String name);

    List<Library> findByPerson(Person person);

    Optional<Library> findByNameAndPerson(String name, Person person);
}
