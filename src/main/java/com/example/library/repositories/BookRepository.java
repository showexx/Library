package com.example.library.repositories;

import com.example.library.models.Book;
import com.example.library.models.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findByLibrary(Library library);
}
