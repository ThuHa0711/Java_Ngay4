package com.example.Java_Ngay4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankReport {

    public static double getTotalBalance(Bank bank) {
//        return bank.getAccounts().values().parallelStream()
//                .mapToDouble(Account::getBalance)
//                .sum();
        double totalBalance = 0.0;
        Map<Integer, Account> accounts = bank.getAccounts();
        for (Account account : accounts.values()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public static List<Account> getHighBalanceAccounts(Bank bank, double threshold) {
//        return bank.getAccounts().values().parallelStream()
//                .filter(account -> account.getBalance() > threshold)
//                .collect(Collectors.toList());
        List<Account> highBalanceAccounts = new ArrayList<>();
        Map<Integer, Account> accounts = bank.getAccounts();
        for (Account account : accounts.values()) {
            if (account.getBalance() > threshold) {
                highBalanceAccounts.add(account);
            }
        }
        return highBalanceAccounts;
    }

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
