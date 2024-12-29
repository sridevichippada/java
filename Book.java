package com.library;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private String isbn;
    private boolean available;  // If true, the book is available for borrowing, false means it's issued

    public Book(int bookID, String title, String author, String isbn, boolean available) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "BookID: " + bookID + ", Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + available;
    }
}
