package com;

public interface BookInterface {
     void borrowBook(User user);
     void returnBook();
     String getTitle();
     boolean isAvailable();
}