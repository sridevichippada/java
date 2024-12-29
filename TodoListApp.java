package com.todo;

import java.util.*;

class Task {
    private String description;
    private int priority;
    private boolean isCompleted;

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
        this.isCompleted = false; // Tasks are incomplete by default
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void deleteTask() {
        this.description = null;
        this.priority = -1;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Priority: " + priority + ", Completed: " + (isCompleted ? "Yes" : "No");
    }
}

public class TodoListApp {
    private static List<Task> tasks;

    public TodoListApp() {
        tasks = new ArrayList<>();
    }

    // Method to add a new task
    public void addTask(String description, int priority) {
        tasks.add(new Task(description, priority));
        System.out.println("Task added successfully!");
    }

    // Method to view pending tasks
    public void viewPendingTasks() {
        System.out.println("\n--- Pending Tasks ---");
        boolean hasPendingTasks = false;
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println(task);
                hasPendingTasks = true;
            }
        }
        if (!hasPendingTasks) {
            System.out.println("No pending tasks.");
        }
    }

    // Method to view completed tasks
    public void viewCompletedTasks() {
        System.out.println("\n--- Completed Tasks ---");
        boolean hasCompletedTasks = false;
        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.println(task);
                hasCompletedTasks = true;
            }
        }
        if (!hasCompletedTasks) {
            System.out.println("No completed tasks.");
        }
    }

    // Method to mark a task as completed
    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Method to delete a task
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Main menu system
    public static void main(String[] args) {
        TodoListApp app = new TodoListApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Pending Tasks");
            System.out.println("3. View Completed Tasks");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1: // Add Task
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task priority (1 - Low, 2 - Medium, 3 - High): ");
                    int priority = scanner.nextInt();
                    app.addTask(description, priority);
                    break;

                case 2: // View Pending Tasks
                    app.viewPendingTasks();
                    break;

                case 3: // View Completed Tasks
                    app.viewCompletedTasks();
                    break;

                case 4: // Mark Task as Completed
                    System.out.print("Enter task index to mark as completed: ");
                    int completeIndex = scanner.nextInt();
                    app.markTaskAsCompleted(completeIndex);
                    break;

                case 5: // Delete Task
                    System.out.print("Enter task index to delete: ");
                    int deleteIndex = scanner.nextInt();
                    app.deleteTask(deleteIndex);
                    break;

                case 6: // Exit
                    System.out.println("Exiting To-Do List application. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
