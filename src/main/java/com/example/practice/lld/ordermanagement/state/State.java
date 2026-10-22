package com.example.practice.lld.ordermanagement.state;

import com.example.practice.lld.ordermanagement.model.Address;
import com.example.practice.lld.ordermanagement.model.Order;
import com.example.practice.lld.ordermanagement.model.Product;
import com.example.practice.lld.ordermanagement.model.User;
import com.example.practice.lld.ordermanagement.service.OrderManager;
import com.example.practice.lld.ordermanagement.strategy.PaymentProcessor;

public interface State {
    void addItems(User user, Product product, int quantity, OrderManager context);
    Order placeOrder(User user, Address address, OrderManager context);
    void processPayment(Order order, PaymentProcessor paymentProcessor, OrderManager context);
    void shipOrder(Order order, OrderManager context);
}
