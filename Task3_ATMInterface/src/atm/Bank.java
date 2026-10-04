package atm;

import java.util.HashMap;

public class Bank {

    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();

        // Sample accounts
        accounts.put("user001", new Account("user001", "1234", 10000));
        accounts.put("user002", new Account("user002", "5678", 5000));
    }

    public Account findAccount(String userId) {
        return accounts.get(userId);
    }

    public boolean accountExists(String userId) {
        return accounts.containsKey(userId);
    }
}