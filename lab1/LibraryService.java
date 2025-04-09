package com;

import java.util.ArrayList;
import java.util.List;

// Repository interface
interface BookRepositoryInterface {
    void addBook(Book book);
    Book findBook(String title);
    List<Book> getAllBooks();
}

// Repository implementation
class BookRepository implements BookRepositoryInterface {
    private List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book findBook(String title) {
        return books.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElse(null);
    }
    
    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}

// Library service interface
interface LibraryServiceInterface {
    void addBook(Book book);
    void borrowBook(String title, User user);
    void returnBook(String title);
}

// Library service implementation
public class LibraryService implements LibraryServiceInterface {
    private BookRepositoryInterface repository;
    private BookLogger logger;

    public LibraryService() {
        this.repository = new BookRepository();
        this.logger = new BookLogger();
    }
    
    public LibraryService(BookRepositoryInterface repository, BookLogger logger) {
        this.repository = repository;
        this.logger = logger;
    }
    
    @Override
    public void addBook(Book book) {
        repository.addBook(book);
    }

    @Override
    public void borrowBook(String title, User user) {
        Book book = repository.findBook(title);
        if (book == null) {
            System.out.println(title + " is not found in the library.");
        } else if (!book.isAvailable()) {
            logger.logNotAvailable(book);
        } else {
            book.borrowBook(user);
            logger.logBorrow(user, book);
        }
    }

    @Override
    public void returnBook(String title) {
        Book book = repository.findBook(title);
        if (book != null) {
            book.returnBook();
            logger.logReturn(book);
        } else {
            System.out.println(title + " is not found in the library.");
        }
    }
}