import java.util.ArrayList;
import java.util.Scanner;

class Account {
    int accNo;
    String name;
    double balance;
    ArrayList<String> transactions;

    Account(int accNo, String name, double initialDeposit) {
        this.accNo = accNo;
        this.name = name;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add("Account opened with ₹" + initialDeposit);
    }

    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        balance += amount;
        transactions.add("Deposited: ₹" + amount + " | Balance: ₹" + balance);
        System.out.println("₹" + amount + " deposited successfully!");
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance! Current: ₹" + balance);
        } else {
            balance -= amount;
            transactions.add("Withdrawn: ₹" + amount + " | Balance: ₹" + balance);
            System.out.println("₹" + amount + " withdrawn successfully!");
        }
    }

    void checkBalance() {
        System.out.println("Account Holder: " + name);
        System.out.println("Account No: " + accNo);
        System.out.println("Current Balance: ₹" + balance);
    }

    void showTransactions() {
        System.out.println("\n--- Transaction History for " + name + " ---");
        for (String t : transactions) {
            System.out.println(t);
        }
    }
}

public class BankAccountSystem {
    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int accCounter = 1001; // Auto-generate account numbers

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: depositMoney(); break;
                case 3: withdrawMoney(); break;
                case 4: checkBalance(); break;
                case 5: viewTransactions(); break;
                case 6: System.out.println("Thank you for banking with us!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void showMenu() {
        System.out.println("\n--- Bank Account System ---");
        System.out.println("1: Create New Account");
        System.out.println("2: Deposit Money");
        System.out.println("3: Withdraw Money");
        System.out.println("4: Check Balance");
        System.out.println("5: View Transaction History");
        System.out.println("6: Exit");
    }

    static Account findAccount(int accNo) {
        for (Account a : accounts) {
            if (a.accNo == accNo) return a;
        }
        return null;
    }

    static void createAccount() {
        sc.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Deposit: ₹");
        double deposit = sc.nextDouble();

        if (deposit < 500) {
            System.out.println("Minimum initial deposit is ₹500");
            return;
        }

        Account newAcc = new Account(accCounter, name, deposit);
        accounts.add(newAcc);
        System.out.println("Account created! Your Account No: " + accCounter);
        accCounter++;
    }

    static void depositMoney() {
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);

        if (acc == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter amount to deposit: ₹");
        double amount = sc.nextDouble();
        acc.deposit(amount);
    }

    static void withdrawMoney() {
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);

        if (acc == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter amount to withdraw: ₹");
        double amount = sc.nextDouble();
        acc.withdraw(amount);
    }

    static void checkBalance() {
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);

        if (acc == null) {
            System.out.println("Account not found!");
        } else {
            acc.checkBalance();
        }
    }

    static void viewTransactions() {
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);

        if (acc == null) {
            System.out.println("Account not found!");
        } else {
            acc.showTransactions();
        }
    }
}