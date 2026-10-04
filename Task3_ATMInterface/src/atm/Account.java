package atm;

import java.util.ArrayList;

public class Account {

    private String userId;
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: ₹" + amount);
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            return true;
        }
        return false;
    }

    public boolean transfer(double amount, Account receiver) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.balance += amount;

            transactionHistory.add(
                    "Transferred: ₹" + amount + " to " + receiver.userId
            );

            receiver.transactionHistory.add(
                    "Received: ₹" + amount + " from " + userId
            );

            return true;
        }
        return false;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}