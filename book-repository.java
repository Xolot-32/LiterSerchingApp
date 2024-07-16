package com.example.gutendexclient.repository;

import com.example.gutendexclient.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguagesContaining(String language);

    @Query("SELECT b.languages, COUNT(b) FROM Book b GROUP BY b.languages")
    List<Object[]> countBooksByLanguage();
}
