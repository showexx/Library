package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findByName(String name);

    List<Book> findByLibrary(Library library);

    Optional<Book> findByNameAndLibrary(String name, Library library);
}
