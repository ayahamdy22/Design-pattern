package com;

public class ExternalBook {
    private String bookTitle;
    private String authorName;
    private int year;
    private boolean isBorrowable;

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public ExternalBook(String bookTitle, String authorName, int year, boolean isBorrowable) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.year = year;
        this.isBorrowable = isBorrowable;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYear() {
        return year;
    }

    public boolean isBorrowable() {
        return isBorrowable;
    }

    public void setBorrowable(boolean borrowable) {
        isBorrowable = borrowable;
    }
}

// Adapter pattern to integrate ExternalBook into our system
class ExternalBookAdapter extends Book {
    private ExternalBook externalBook;
    
    public ExternalBookAdapter(ExternalBook externalBook) {
        super(externalBook.getBookTitle());
        this.externalBook = externalBook;
    }
    
    @Override
    public void borrowBook(User user) {
        if (externalBook.isBorrowable()) {
            super.borrowBook(user);
            externalBook.setBorrowable(false);
        }
    }
    
    @Override
    public void returnBook() {
        super.returnBook();
        externalBook.setBorrowable(true);
    }
}