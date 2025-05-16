package com.example.Java_Ngay4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankReport {

    // Tính tổng số dư của tất cả các tài khoản trong ngân hàng
    public static double getTotalBalance(Bank bank) {
        double totalBalance = 0.0;
        Map<Integer, Account> accounts = bank.getAccounts();
        for (Account account : accounts.values()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    // Lấy danh sách các tài khoản có số dư vượt ngưỡng cho trước
    public static List<Account> getHighBalanceAccounts(Bank bank, double threshold) {
        List<Account> highBalanceAccounts = new ArrayList<>();
        Map<Integer, Account> accounts = bank.getAccounts();
        for (Account account : accounts.values()) {
            if (account.getBalance() > threshold) {
                highBalanceAccounts.add(account);
            }
        }
        return highBalanceAccounts;
    }

    // In ra báo cáo tổng hợp ngân hàng
    public static void printReport(Bank bank, double threshold) {
        double totalBalance = getTotalBalance(bank);
        List<Account> highBalanceAccounts = getHighBalanceAccounts(bank, threshold);

        System.out.println("=== Báo Cáo Ngân Hàng ===");
        System.out.printf("Tổng số tiền còn lại: %.2f%n", totalBalance);
        System.out.println("Danh sách tài khoản có số dư vượt ngưỡng " + threshold + ":");
        highBalanceAccounts.forEach(account ->
                System.out.printf(" - Tài khoản %d: %.2f%n", account.getAccountId(), account.getBalance())
        );
    }
}
