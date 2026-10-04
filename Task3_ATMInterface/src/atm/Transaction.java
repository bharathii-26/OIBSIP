package atm;

public class Transaction {

    private String type;
    private double amount;
    private String details;

    public Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return type + " | Amount: ₹" + amount + " | " + details;
    }
}