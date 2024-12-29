package com.library;

public class Borrower {
    private int borrowerID;
    private String name;
    private int borrowedBookID; // 0 if no book is borrowed

    public Borrower(int borrowerID, String name, int borrowedBookID) {
        this.borrowerID = borrowerID;
        this.name = name;
        this.borrowedBookID = borrowedBookID;
    }

    public int getBorrowerID() {
        return borrowerID;
    }

    public String getName() {
        return name;
    }

    public int getBorrowedBookID() {
        return borrowedBookID;
    }

    public void setBorrowedBookID(int borrowedBookID) {
        this.borrowedBookID = borrowedBookID;
    }

    @Override
    public String toString() {
        return "BorrowerID: " + borrowerID + ", Name: " + name + ", BorrowedBookID: " + borrowedBookID;
    }
}
