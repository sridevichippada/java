package com.tracker;

import java.util.*;

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: $" + amount + ", Category: " + category;
    }
}

public class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    // Method to add a new expense
    public void addExpense(String description, double amount, String category) {
        Expense expense = new Expense(description, amount, category);
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    // Method to view all expenses
    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        System.out.println("\n--- All Expenses ---");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    // Method to view expenses by category
    public void viewExpensesByCategory(String category) {
        boolean found = false;
        System.out.println("\n--- Expenses for category: " + category + " ---");
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found for this category.");
        }
    }

    // Method to calculate total expenses
    public void calculateTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.printf("Total expenses: $%.2f\n", total);
    }

    // Main menu to interact with the user
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses by Category");
            System.out.println("4. Calculate Total Expenses");
            System.out.println("5. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1: // Add Expense
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    tracker.addExpense(description, amount, category);
                    break;

                case 2: // View All Expenses
                    tracker.viewAllExpenses();
                    break;

                case 3: // View Expenses by Category
                    System.out.print("Enter category to filter by: ");
                    String filterCategory = scanner.nextLine();
                    tracker.viewExpensesByCategory(filterCategory);
                    break;

                case 4: // Calculate Total Expenses
                    tracker.calculateTotalExpenses();
                    break;

                case 5: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}


