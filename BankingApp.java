package com.pack1;

import java.util.*;

class User {
    private String username;
    private String password;
    private double balance;
    private List<String> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        transactionHistory.add("Withdrawn: $" + amount);
    }

    public void transfer(User recipient, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        this.balance -= amount;
        recipient.balance += amount;
        transactionHistory.add("Transferred: $" + amount + " to " + recipient.username);
        recipient.transactionHistory.add("Received: $" + amount + " from " + this.username);
    }

    public void viewStatement() {
        System.out.println("Account Statement for " + username + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.printf("Current Balance: $%.2f\n", balance);
    }
}

class Admin {
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    public boolean validateAdmin(String username, String password) {
        return adminUsername.equals(username) && adminPassword.equals(password);
    }
}

class BankingSystem {
    private Map<String, User> users;
    private Map<String, User> pendingUsers;
    private Admin admin;

    public BankingSystem() {
        this.users = new HashMap<>();
        this.pendingUsers = new HashMap<>();
        this.admin = new Admin();
    }

    public void registerUser(String username, String password) {
        if (users.containsKey(username) || pendingUsers.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }
        User newUser = new User(username, password);
        pendingUsers.put(username, newUser);
        System.out.println("User " + username + " added to pending approval list.");
    }

    public void approveUser(String adminUsername, String adminPassword) {
        if (!admin.validateAdmin(adminUsername, adminPassword)) {
            System.out.println("Invalid admin credentials.");
            return;
        }
        if (pendingUsers.isEmpty()) {
            System.out.println("No users pending approval.");
            return;
        }
        System.out.println("Pending Users for Approval:");
        for (String username : pendingUsers.keySet()) {
            System.out.println(" - " + username);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username to approve: ");
        String approvedUsername = scanner.nextLine();
        if (pendingUsers.containsKey(approvedUsername)) {
            users.put(approvedUsername, pendingUsers.remove(approvedUsername));
            System.out.println("User " + approvedUsername + " is now approved and added to active accounts.");
        } else {
            System.out.println("User not found in pending list.");
        }
    }

    public User userLogin(String username, String password) {
        if (!users.containsKey(username)) {
            System.out.println("User not found.");
            return null;
        }
        User user = users.get(username);
        if (user.getPassword().equals(password)) {
            System.out.println("Welcome back, " + username + "!");
            return user;
        } else {
            System.out.println("Invalid password.");
            return null;
        }
    }

    public void deposit(User user, double amount) {
        try {
            user.deposit(amount);
            System.out.printf("Deposited $%.2f successfully.\n", amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void withdraw(User user, double amount) {
        try {
            user.withdraw(amount);
            System.out.printf("Withdrew $%.2f successfully.\n", amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transfer(User user, String recipientUsername, double amount) {
        if (!users.containsKey(recipientUsername)) {
            System.out.println("Recipient not found.");
            return;
        }
        User recipient = users.get(recipientUsername);
        try {
            user.transfer(recipient, amount);
            System.out.printf("Transferred $%.2f to %s successfully.\n", amount, recipientUsername);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewStatement(User user) {
        user.viewStatement();
    }
}

public class BankingApp {
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Bank System ---");
            System.out.println("1. Register");
            System.out.println("2. Admin Login");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                bankingSystem.registerUser(username, password);

            } else if (choice.equals("2")) {
                System.out.print("Enter admin username: ");
                String adminUsername = scanner.nextLine();
                System.out.print("Enter admin password: ");
                String adminPassword = scanner.nextLine();
                bankingSystem.approveUser(adminUsername, adminPassword);

            } else if (choice.equals("3")) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User user = bankingSystem.userLogin(username, password);

                if (user != null) {
                    while (true) {
                        System.out.println("\n--- User Menu ---");
                        System.out.println("1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Transfer");
                        System.out.println("4. View Statement");
                        System.out.println("5. Logout");
                        System.out.print("Select an option: ");
                        String userChoice = scanner.nextLine();

                        if (userChoice.equals("1")) {
                            System.out.print("Enter deposit amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            bankingSystem.deposit(user, amount);

                        } else if (userChoice.equals("2")) {
                            System.out.print("Enter withdrawal amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            bankingSystem.withdraw(user, amount);

                        } else if (userChoice.equals("3")) {
                            System.out.print("Enter recipient username: ");
                            String recipientUsername = scanner.nextLine();
                            System.out.print("Enter transfer amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            bankingSystem.transfer(user, recipientUsername, amount);

                        } else if (userChoice.equals("4")) {
                            bankingSystem.viewStatement(user);

                        } else if (userChoice.equals("5")) {
                            System.out.println("Goodbye, " + username + "!");
                            break;

                        } else {
                            System.out.println("Invalid option.");
                        }
                    }
                }

            } else if (choice.equals("4")) {
                System.out.println("Exiting system.");
                break;

            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
