import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;
    String email;
    String address;

    Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String toString() {
        return "Name: " + name + " | Phone: " + phone + 
               " | Email: " + email + " | Address: " + address;
    }
}

public class ContactManagementSystem {
    static ArrayList<Contact> contacts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewAllContacts(); break;
                case 3: searchContact(); break;
                case 4: updateContact(); break;
                case 5: deleteContact(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void showMenu() {
        System.out.println("\n--- Contact Management ---");
        System.out.println("1: Add Contact");
        System.out.println("2: View All Contacts");
        System.out.println("3: Search Contact by Name");
        System.out.println("4: Update Contact");
        System.out.println("5: Delete Contact");
        System.out.println("6: Exit");
    }

    static void addContact() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        if (findContactByName(name) != null) {
            System.out.println("Contact with this name already exists!");
            return;
        }

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        contacts.add(new Contact(name, phone, email, address));
        System.out.println("Contact added successfully!");
    }

    static void viewAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found!");
            return;
        }
        System.out.println("\n--- All Contacts ---");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }

    static Contact findContactByName(String name) {
        for (Contact c : contacts) {
            if (c.name.equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    static void searchContact() {
        System.out.print("Enter Name to search: ");
        String name = sc.nextLine();
        Contact c = findContactByName(name);
        
        if (c != null) System.out.println("Found: " + c);
        else System.out.println("Contact not found!");
    }

    static void updateContact() {
        System.out.print("Enter Name to update: ");
        String name = sc.nextLine();
        Contact c = findContactByName(name);

        if (c == null) {
            System.out.println("Contact not found!");
            return;
        }

        System.out.print("Enter new Phone: ");
        c.phone = sc.nextLine();
        System.out.print("Enter new Email: ");
        c.email = sc.nextLine();
        System.out.print("Enter new Address: ");
        c.address = sc.nextLine();
        System.out.println("Contact updated successfully!");
    }

    static void deleteContact() {
        System.out.print("Enter Name to delete: ");
        String name = sc.nextLine();
        Contact c = findContactByName(name);

        if (c != null) {
            contacts.remove(c);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }
}