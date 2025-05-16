package com.example.Java_Ngay4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Bank {
    private final Map<Integer, Account> accounts = new ConcurrentHashMap<>();
    private final Semaphore semaphore = new Semaphore(10); // Giới hạn tối đa 10 giao dịch cùng lúc

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public Account getAccount(int accountId) {
        return accounts.get(accountId);
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount == null || toAccount == null) return false;

        try {
            semaphore.acquire(); // Giới hạn số lượng giao dịch
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
