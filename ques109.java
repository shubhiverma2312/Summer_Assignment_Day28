import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    boolean isIssued;
    String issuedTo;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }

    public String toString() {
        String status = isIssued ? "Issued to " + issuedTo : "Available";
        return "ID: " + bookId + " | " + title + " by " + author + " | " + status;
    }
}

public class LibraryManagementSystem {
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Add some default books
        books.add(new Book(101, "The Alchemist", "Paulo Coelho"));
        books.add(new Book(102, "Java: Complete Reference", "Herbert Schildt"));
        books.add(new Book(103, "Wings of Fire", "A.P.J Abdul Kalam"));

        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewAllBooks(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: searchBook(); break;
                case 6: System.out.println("Exiting Library System..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void showMenu() {
        System.out.println("\n--- Library Management ---");
        System.out.println("1: Add New Book");
        System.out.println("2: View All Books");
        System.out.println("3: Issue Book");
        System.out.println("4: Return Book");
        System.out.println("5: Search Book by ID");
        System.out.println("6: Exit");
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findBookById(id) != null) {
            System.out.println("Book ID already exists!");
            return;
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library!");
            return;
        }
        System.out.println("\n--- All Books ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    static Book findBookById(int id) {
        for (Book b : books) {
            if (b.bookId == id) return b;
        }
        return null;
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book b = findBookById(id);

        if (b == null) {
            System.out.println("Book not found!");
        } else if (b.isIssued) {
            System.out.println("Book already issued to " + b.issuedTo);
        } else {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            b.isIssued = true;
            b.issuedTo = name;
            System.out.println("Book issued to " + name + " successfully!");
        }
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        Book b = findBookById(id);

        if (b == null) {
            System.out.println("Book not found!");
        } else if (!b.isIssued) {
            System.out.println("Book was not issued!");
        } else {
            System.out.println("Book returned by " + b.issuedTo);
            b.isIssued = false;
            b.issuedTo = null;
        }
    }

    static void searchBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        Book b = findBookById(id);
        if (b != null) System.out.println("Found: " + b);
        else System.out.println("Book not found!");
    }
}