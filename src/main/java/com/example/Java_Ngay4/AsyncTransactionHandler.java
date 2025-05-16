package com.example.Java_Ngay4;

import java.util.concurrent.CompletableFuture;

public class AsyncTransactionHandler {

    public static void sendTransactionEmail(int accountId, double amount, boolean success) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(100); // Giả lập thời gian gửi email
                if (success) {
                    System.out.printf("Email: Giao dịch thành công! Tài khoản %d nhận được số tiền %.2f%n", accountId, amount);
                } else {
                    System.out.printf("Email: Giao dịch thất bại! Không thể rút số tiền %.2f từ tài khoản %d%n", amount, accountId);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public static void generateTransactionReport(int fromAccount, int toAccount, double amount) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(150); // Giả lập thời gian tạo báo cáo
                System.out.printf("Báo cáo: Giao dịch từ %d đến %d số tiền %.2f đã được xử lý.%n", fromAccount, toAccount, amount);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
