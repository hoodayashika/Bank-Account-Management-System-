package com.example.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountManagerTest {
    private BankAccountManager manager;

    @BeforeEach
    public void setUp() {
        manager = new BankAccountManager();
    }

    @Test
    public void testCreateAccount() {
        manager.createAccount("12345", "John Doe", 1000.0);
        BankAccount account = manager.getAccount("12345");
        assertEquals("12345", account.getAccountNumber());
        assertEquals("John Doe", account.getAccountHolder());
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    public void testDepositToAccount() {
        manager.createAccount("12345", "John Doe", 1000.0);
        manager.depositToAccount("12345", 500.0);
        BankAccount account = manager.getAccount("12345");
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    public void testWithdrawFromAccount() {
        manager.createAccount("12345", "John Doe", 1000.0);
        manager.withdrawFromAccount("12345", 400.0);
        BankAccount account = manager.getAccount("12345");
        assertEquals(600.0, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        manager.createAccount("12345", "John Doe", 1000.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.withdrawFromAccount("12345", 1500.0);
        });
        assertEquals("Insufficient funds.", exception.getMessage());
    }

    @Test
    public void testDepositNegativeAmount() {
        manager.createAccount("12345", "John Doe", 1000.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.depositToAccount("12345", -500.0);
        });
        assertEquals("Deposit amount must be positive.", exception.getMessage());
    }

    @Test
    public void testWithdrawNegativeAmount() {
        manager.createAccount("12345", "John Doe", 1000.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.withdrawFromAccount("12345", -500.0);
        });
        assertEquals("Withdrawal amount must be positive.", exception.getMessage());
    }
}
