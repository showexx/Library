package com.example.library.repository;

import com.example.library.model.Library;
import com.example.library.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Integer> {
    Optional<Library> findByPersonAndAndName(Person person, String name);
}