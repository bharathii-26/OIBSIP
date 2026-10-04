package atm;

import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("==============================");
        System.out.println("        ATM INTERFACE");
        System.out.println("==============================");

        Account account = login();

        if (account == null) {
            System.out.println("Account locked. Please try again later.");
            return;
        }

        showMenu(account);
    }

    private Account login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = scanner.next();

            Account account = bank.findAccount(userId);

            if (account == null) {
                System.out.println("Invalid User ID.");
                attempts++;
                continue;
            }

            System.out.print("Enter PIN: ");
            String pin = scanner.next();

            if (account.checkPin(pin)) {
                System.out.println("\nLogin successful!");
                return account;
            }

            attempts++;

            System.out.println("Incorrect PIN.");

            if (attempts < 3) {
                System.out.println(
                        "Attempts remaining: " + (3 - attempts)
                );
            }
        }

        return null;
    }

    private void showMenu(Account account) {

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("          ATM MENU");
            System.out.println("==============================");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");

            System.out.print("Choose an option: ");

            int choice;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Please enter a valid option.");
                scanner.next();
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.println(
                            "Current Balance: ₹" + account.getBalance()
                    );
                    break;

                case 2:
                    withdraw(account);
                    break;

                case 3:
                    deposit(account);
                    break;

                case 4:
                    transfer(account);
                    break;

                case 5:
                    showHistory(account);
                    break;

                case 6:
                    System.out.println("Thank you for using the ATM.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void withdraw(Account account) {

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
            System.out.println(
                    "Remaining Balance: ₹" + account.getBalance()
            );
        } else {
            System.out.println(
                    "Withdrawal failed. Check amount or balance."
            );
        }
    }

    private void deposit(Account account) {

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (account.deposit(amount)) {
            System.out.println("Deposit successful.");
            System.out.println(
                    "Updated Balance: ₹" + account.getBalance()
            );
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void transfer(Account account) {

        System.out.print("Enter receiver User ID: ");
        String receiverId = scanner.next();

        Account receiver = bank.findAccount(receiverId);

        if (receiver == null) {
            System.out.println("Receiver account not found.");
            return;
        }

        if (receiver == account) {
            System.out.println("Cannot transfer to your own account.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (account.transfer(amount, receiver)) {
            System.out.println("Transfer successful.");
            System.out.println(
                    "Remaining Balance: ₹" + account.getBalance()
            );
        } else {
            System.out.println(
                    "Transfer failed. Check amount or balance."
            );
        }
    }

    private void showHistory(Account account) {

        System.out.println("\n==============================");
        System.out.println("     TRANSACTION HISTORY");
        System.out.println("==============================");

        if (account.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (String transaction : account.getTransactionHistory()) {
            System.out.println("- " + transaction);
        }
    }
}