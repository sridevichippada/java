package com.library;

import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    private static final String BOOKS_FILE = "books.csv";
    private static final String BORROWERS_FILE = "borrowers.csv";
    private List<Book> books;
    private List<Borrower> borrowers;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
        loadBooksFromCSV();
        loadBorrowersFromCSV();
    }

    // Method to load books from CSV
    private void loadBooksFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int bookID = Integer.parseInt(values[0]);
                String title = values[1];
                String author = values[2];
                String isbn = values[3];
                boolean available = Boolean.parseBoolean(values[4]);
                books.add(new Book(bookID, title, author, isbn, available));
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }

    // Method to load borrowers from CSV
    private void loadBorrowersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(BORROWERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int borrowerID = Integer.parseInt(values[0]);
                String name = values[1];
                int borrowedBookID = Integer.parseInt(values[2]);
                borrowers.add(new Borrower(borrowerID, name, borrowedBookID));
            }
        } catch (IOException e) {
            System.out.println("Error reading borrowers file: " + e.getMessage());
        }
    }

    // Method to save books to CSV
    private void saveBooksToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                bw.write(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getIsbn() + "," + book.isAvailable() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    // Method to save borrowers to CSV
    private void saveBorrowersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BORROWERS_FILE))) {
            for (Borrower borrower : borrowers) {
                bw.write(borrower.getBorrowerID() + "," + borrower.getName() + "," + borrower.getBorrowedBookID() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving borrowers to file: " + e.getMessage());
        }
    }

    // Add a new book
    public void addBook(String title, String author, String isbn) {
        int bookID = books.size() + 1;
        books.add(new Book(bookID, title, author, isbn, true));
        saveBooksToCSV();
        System.out.println("Book added successfully.");
    }

    // Issue a book to a borrower
    public void issueBook(int bookID, int borrowerID) {
        Book book = findBookByID(bookID);
        if (book != null && book.isAvailable()) {
            Borrower borrower = findBorrowerByID(borrowerID);
            if (borrower != null) {
                borrower.setBorrowedBookID(bookID);
                book.setAvailable(false);
                saveBooksToCSV();
                saveBorrowersToCSV();
                System.out.println("Book issued successfully to " + borrower.getName());
            } else {
                System.out.println("Borrower not found.");
            }
        } else {
            System.out.println("Book not available or invalid book ID.");
        }
    }

    // Find a book by its ID
    private Book findBookByID(int bookID) {
        for (Book book : books) {
            if (book.getBookID() == bookID) {
                return book;
            }
        }
        return null;
    }

    // Find a borrower by their ID
    private Borrower findBorrowerByID(int borrowerID) {
        for (Borrower borrower : borrowers) {
            if (borrower.getBorrowerID() == borrowerID) {
                return borrower;
            }
        }
        return null;
    }

    // View all available books
    public void viewAvailableBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    // View all issued books
    public void viewIssuedBooks() {
        System.out.println("\nIssued Books:");
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. View Available Books");
            System.out.println("4. View Issued Books");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    system.addBook(title, author, isbn);
                    break;
                case 2:
                    System.out.print("Enter book ID to issue: ");
                    int bookID = scanner.nextInt();
                    System.out.print("Enter borrower ID: ");
                    int borrowerID = scanner.nextInt();
                    system.issueBook(bookID, borrowerID);
                    break;
                case 3:
                    system.viewAvailableBooks();
                    break;
                case 4:
                    system.viewIssuedBooks();
                    break;
                case 5:
                    System.out.println("Exiting Library System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
