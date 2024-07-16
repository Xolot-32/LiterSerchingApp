package com.example.gutendexclient.service;

import com.example.gutendexclient.model.Book;
import com.example.gutendexclient.util.JsonParser;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private final HttpClient httpClient;
    private final JsonParser jsonParser;

    public GutendexService(JsonParser jsonParser) {
        this.httpClient = HttpClient.newHttpClient();
        this.jsonParser = jsonParser;
    }

    public List<Book> searchBooksByTitle(String title) throws Exception {
        List<Book> allBooks = new ArrayList<>();
        String nextUrl = BASE_URL + "?search=" + title.replace(" ", "+");

        while (nextUrl != null && allBooks.size() < 100) { // Limitar a 100 libros para evitar sobrecarga
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(nextUrl))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonParser.ParseResult result = jsonParser.parseBookList(response.body());
                allBooks.addAll(result.getBooks());
                nextUrl = result.getNextUrl();
            } else {
                throw new RuntimeException("Error al buscar libros: " + response.statusCode());
            }
        }

        return allBooks;
    }
}
