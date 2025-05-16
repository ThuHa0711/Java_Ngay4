package com.example.Java_Ngay4;

public class TransactionTask implements Runnable {

    private final Bank bank;
    private final int fromAccountId;
    private final int toAccountId;
    private final double amount;

    public TransactionTask(Bank bank, int fromAccountId, int toAccountId, double amount) {
        this.bank = bank;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    @Override
    public void run() {
        boolean success = bank.transfer(fromAccountId, toAccountId, amount);

        // Gửi email xác nhận không đồng bộ
        AsyncTransactionHandler.sendTransactionEmail(toAccountId, amount, success);

        // Tạo báo cáo giao dịch không đồng bộ
        AsyncTransactionHandler.generateTransactionReport(fromAccountId, toAccountId, amount);
    }
}
