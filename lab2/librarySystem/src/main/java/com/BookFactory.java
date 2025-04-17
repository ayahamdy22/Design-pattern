package com;

public class BookFactory {
    public static Borrowable createBook(String type, String title) {
        String lowerCaseType = type.toLowerCase();
        switch (lowerCaseType) {
            case "physical":
                return new PhysicalBook(title);
            case "historical":
                return new HistoricalBook(title);
            case "ebook":
                return new EBook(title);
            default:
                throw new IllegalArgumentException("Invalid book type: " + type);
        }
    }
}