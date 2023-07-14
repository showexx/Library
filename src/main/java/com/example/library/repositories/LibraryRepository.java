package com.example.library.repositories;

import com.example.library.models.Library;
import com.example.library.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Integer> {
    Optional<Library> findByPerson(Person person);
}
