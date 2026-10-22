package com.example.practice.lld.ordermanagement.strategy;

public class UPIPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
        return true;
    }
}
