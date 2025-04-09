package com;

public abstract class Book implements BookInterface {
    private String title;
    private boolean isAvailable;

    public Book(String title) {
        this.title = title;
        this.isAvailable = true;
    }

    @Override
    public void borrowBook(User user) {
        if (isAvailable) {
            isAvailable = false;
        }
    }

    @Override
    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }
}

// Standard book implementation
class StandardBook extends Book {
    public StandardBook(String title) {
        super(title);
    }
}

// Logger class remains in same file as per original structure
class BookLogger {
    public void logBorrow(User user, BookInterface book) {
        System.out.printf("%s successfully borrowed %s%n", user.getName(), book.getTitle());
    }

    public void logReturn(BookInterface book) {
        System.out.println(book.getTitle() + " has been returned.");
    }

    public void logNotAvailable(BookInterface book) {
        System.out.println(book.getTitle() + " is not available.");
    }
}