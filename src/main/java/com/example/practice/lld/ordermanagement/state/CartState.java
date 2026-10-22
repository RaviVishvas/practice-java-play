package com.example.practice.lld.ordermanagement.state;

import com.example.practice.lld.ordermanagement.model.*;
import com.example.practice.lld.ordermanagement.service.CartManager;
import com.example.practice.lld.ordermanagement.service.OrderManager;
import com.example.practice.lld.ordermanagement.strategy.PaymentProcessor;

import java.util.Map;

public class CartState implements State{

    @Override
    public void addItems(User user, Product product, int quantity, OrderManager state) {
        CartManager cartManager = state.getCartManager();
        cartManager.addIntoCart(user, Map.of(product, quantity));
        state.currState = new OrderPlaceState();
        System.out.println(quantity+ " "+product.getProductName()+ " added in cart ");
    }

    @Override
    public Order placeOrder(User user, Address address, OrderManager state) {
        System.out.println("InvalidState");
        return null;
    }

    @Override
    public void processPayment(Order order, PaymentProcessor paymentProcessor, OrderManager state) {
        System.out.println("InvalidState");
    }

    @Override
    public void shipOrder(Order order, OrderManager state) {
        System.out.println("InvalidState");
    }
}
