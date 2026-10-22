package com.example.practice.lld.ordermanagement.strategy;

public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Card payment of $" + amount);
        return true;
    }
}
