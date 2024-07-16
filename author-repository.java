package com.example.gutendexclient.repository;

import com.example.gutendexclient.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE :year BETWEEN a.birthYear AND COALESCE(a.deathYear, :year)")
    List<Author> findAuthorsAliveInYear(@Param("year") int year);
}
