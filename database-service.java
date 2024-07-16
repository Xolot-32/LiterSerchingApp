package com.example.gutendexclient.service;

import com.example.gutendexclient.model.Author;
import com.example.gutendexclient.model.Book;
import com.example.gutendexclient.repository.AuthorRepository;
import com.example.gutendexclient.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DatabaseService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public DatabaseService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsAliveInYear(int year) {
        return authorRepository.findAuthorsAliveInYear(year);
    }

    public Map<String, Long> getBookStatsByLanguage() {
        return bookRepository.countBooksByLanguage().stream()
                .collect(Collectors.toMap(
                        row -> ((String) ((List<?>) row[0]).get(0)),
                        row -> (Long) row[1]
                ));
    }
}
