package com.example.Java_Ngay4;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Bank {
    private final HashMap<Integer, Account> accounts = new HashMap<>();
    private final Semaphore semaphore = new Semaphore(10);

    // Thêm tài khoản mới
    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    // Trả về tài khoản dựa trên ID
    public Account getAccount(int accountId) {
        return accounts.get(accountId);
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    // Giao dịch giữa 2 tài khoản
    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount == null || toAccount == null) return false;

        try {
            semaphore.acquire();
            if (fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                System.out.printf("Chuyển khoản thành công: %d -> %d, Số tiền: %.2f%n", fromAccountId, toAccountId, amount);
                return true;
            } else {
                System.out.printf("Chuyển khoản thất bại: Không đủ tiền trong tài khoản %d%n", fromAccountId);
                return false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaphore.release();
        }
    }
}
