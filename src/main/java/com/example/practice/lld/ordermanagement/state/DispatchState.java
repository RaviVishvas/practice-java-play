package com.example.practice.lld.ordermanagement.state;

import com.example.practice.lld.ordermanagement.enums.OrderStatus;
import com.example.practice.lld.ordermanagement.model.Address;
import com.example.practice.lld.ordermanagement.model.Order;
import com.example.practice.lld.ordermanagement.model.Product;
import com.example.practice.lld.ordermanagement.model.User;
import com.example.practice.lld.ordermanagement.service.OrderManager;
import com.example.practice.lld.ordermanagement.strategy.PaymentProcessor;

public class DispatchState implements State {

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
        System.out.println("Invalid state");
    }

    @Override
    public void shipOrder(Order order, OrderManager state) {
        if (order.getStatus() == OrderStatus.PAID) {
            order.setStatus(OrderStatus.SHIPPED);
            System.out.println("Order " + order.getOrderId() + " has been SHIPPED.");
        } else {
            System.out.println("Order cannot be shipped. Current status: " + order.getStatus());
        }
        state.currState = new CartState();
    }
}
