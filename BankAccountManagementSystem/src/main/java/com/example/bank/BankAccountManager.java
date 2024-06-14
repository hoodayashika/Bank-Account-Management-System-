package com.example.bank;

import java.util.HashMap;
import java.util.Map;

public class BankAccountManager {
    private Map<String, BankAccount> accounts;

    public BankAccountManager() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (!accounts.containsKey(accountNumber)) {
            BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);
            accounts.put(accountNumber, account);
        } else {
            throw new IllegalArgumentException("Account number already exists.");
        }
    }

    public BankAccount getAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return account;
    }

    public void depositToAccount(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        account.withdraw(amount);
    }
}
