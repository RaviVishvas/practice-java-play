package com.example.practice.lld.ordermanagement.state;

import com.example.practice.lld.ordermanagement.enums.OrderStatus;
import com.example.practice.lld.ordermanagement.model.*;
import com.example.practice.lld.ordermanagement.service.CartManager;
import com.example.practice.lld.ordermanagement.service.OrderManager;
import com.example.practice.lld.ordermanagement.strategy.PaymentProcessor;

public class PaymentState implements State {

    @Override
    public void addItems(User user, Product product, int quantity, OrderManager state) {
        System.out.println("Invalid state");
    }

    @Override
    public Order placeOrder(User user, Address address, OrderManager state) {
        System.out.println("Invalid state");
        return null;
    }

    @Override
    public void processPayment(Order order, PaymentProcessor paymentProcessor, OrderManager state) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            System.out.println("Order is not in PENDING_PAYMENT status.");
            return;
        }

        boolean isSuccess = paymentProcessor.processPayment(order.getTotalAmount());
        if (isSuccess) {
            order.setStatus(OrderStatus.PAID);
            System.out.println("Payment successful for Order ID: " + order.getOrderId());
            state.currState = new DispatchState();
        } else {
            System.out.println("Payment failed for Order ID: " + order.getOrderId());
            // In a real system, we might release stock after some timeout
            state.currState = new CartState();
        }
    }

    @Override
    public void shipOrder(Order order, OrderManager state) {
        System.out.println("Invalid state");
    }
}
