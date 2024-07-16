package com.example.gutendexclient.util;

import com.example.gutendexclient.model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    public ParseResult parseBookList(String json) throws Exception {
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode resultsNode = rootNode.get("results");
        String nextUrl = rootNode.get("next").asText(null);

        List<Book> books = new ArrayList<>();
        for (JsonNode bookNode : resultsNode) {
            Book book = objectMapper.treeToValue(bookNode, Book.class);
            books.add(book);
        }

        return new ParseResult(books, nextUrl);
    }

    public static class ParseResult {
        private final List<Book> books;
        private final String nextUrl;

        public ParseResult(List<Book> books, String nextUrl) {
            this.books = books;
            this.nextUrl = nextUrl;
        }

        public List<Book> getBooks() {
            return books;
        }

        public String getNextUrl() {
            return nextUrl;
        }
    }
}
