package com.example.gutendexclient;

import com.example.gutendexclient.model.Author;
import com.example.gutendexclient.model.Book;
import com.example.gutendexclient.service.DatabaseService;
import com.example.gutendexclient.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class GutendexClientApplication implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private DatabaseService databaseService;

    public static void main(String[] args) {
        SpringApplication.run(GutendexClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Listar autores");
            System.out.println("4. Listar autores vivos en un año específico");
            System.out.println("5. Mostrar estadísticas de libros por idioma");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (option) {
                    case 1:
                        searchBookByTitle(scanner);
                        break;
                    case 2:
                        listAllBooks();
                        break;
                    case 3:
                        listAuthors();
                        break;
                    case 4:
                        listAuthorsAliveInYear(scanner);
                        break;
                    case 5:
                        showBookStatsByLanguage();
                        break;
                    case 0:
                        exit = true;
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }

    private void searchBookByTitle(Scanner scanner) throws Exception {
        System.out.print("Ingrese el título del libro a buscar: ");
        String title = scanner.nextLine();
        List<Book> books = gutendexService.searchBooksByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("Libros encontrados:");
            for (Book book : books) {
                System.out.println(book);
                databaseService.saveBook(book);
            }
        }
    }

    private void listAllBooks() {
        List<Book> books = databaseService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No hay libros en la base de datos.");
        } else {
            System.out.println("Libros en la base de datos:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void listAuthors() {
        List<Author> authors = databaseService.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("No hay autores en la base de datos.");
        } else {
            System.out.println("Autores en la base de datos:");
            for (Author author : authors) {
                System.out.println(author);
            }
        }
    }

    private void listAuthorsAliveInYear(Scanner scanner) {
        System.out.print("Ingrese el año: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        List<Author> authors = databaseService.getAuthorsAliveInYear(year);
        if (authors.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + year);
        } else {
            System.out.println("Autores vivos en el año " + year + ":");
            for (Author author : authors) {
                System.out.println(author);
            }
        }
    }

    private void showBookStatsByLanguage() {
        Map<String, Long> stats = databaseService.getBookStatsByLanguage();
        if (stats.isEmpty()) {
            System.out.println("No hay estadísticas disponibles.");
        } else {
            System.out.println("Estadísticas de libros por idioma:");
            for (Map.Entry<String, Long> entry : stats.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " libro(s)");
            }
        }
    }
}
