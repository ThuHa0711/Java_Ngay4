package com.example.Java_Ngay4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankApp {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Random random = new Random();
        int numberOfAccounts = 100;
        int numberOfTransactions = 1000;

        // Khởi tạo 100 tài khoản với số dư ngẫu nhiên từ 1000 đến 10000
        for (int i = 0; i < numberOfAccounts; i++) {
            bank.addAccount(new Account(i, 1000 + random.nextInt(9000)));
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Thực hiện 1000 giao dịch ngẫu nhiên
        for (int i = 0; i < numberOfTransactions; i++) {
            int fromAccountId = random.nextInt(numberOfAccounts);
            int toAccountId = random.nextInt(numberOfAccounts);
            double amount = 100 + random.nextDouble() * 900;

            if (fromAccountId != toAccountId) {
                executor.execute(new TransactionTask(bank, fromAccountId, toAccountId, amount));
            }
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("Giao dịch hoàn thành!");
        System.out.println("Tổng số tài khoản: " + numberOfAccounts);

        // In ra thông tin các tài khoản
        for (int i = 0; i < numberOfAccounts; i++) {
            System.out.printf("Tài khoản %d: %.2f%n", i, bank.getAccount(i).getBalance());
        }

        // Tạo báo cáo tổng hợp sau giao dịch
        BankReport.printReport(bank, 5000);
    }
}
