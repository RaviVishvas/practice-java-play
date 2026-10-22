package com.example.practice.lld.ordermanagement.service;

import com.example.practice.lld.ordermanagement.enums.OrderStatus;
import com.example.practice.lld.ordermanagement.enums.PaymentMode;
import com.example.practice.lld.ordermanagement.model.*;
import com.example.practice.lld.ordermanagement.state.State;
import com.example.practice.lld.ordermanagement.strategy.CardPaymentProcessor;
import com.example.practice.lld.ordermanagement.strategy.PaymentProcessor;
import com.example.practice.lld.ordermanagement.strategy.UPIPaymentProcessor;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderManager {
    private final WarehouseManager warehouseManager;
    private final CartManager cartManager;

    public State currState;

    public OrderManager(WarehouseManager warehouseManager, CartManager cartManager, State state) {
        this.warehouseManager = warehouseManager;
        this.cartManager = cartManager;
        this.currState = state;
    }

    public void addItemsInCart(User user, Product product, int quantity){
        currState.addItems(user, product, quantity, this);
    }

    public Order placeOrder(User user, Address shippingAddress) {
        Order order = currState.placeOrder(user, shippingAddress, this);
        return order;
    }

    public void processPayment(Order order, PaymentMode paymentMode) {

        switch (paymentMode) {
            case UPI -> currState.processPayment(order, new UPIPaymentProcessor(), this);
            case CARD -> currState.processPayment(order, new CardPaymentProcessor(), this);
        }

    }

    public void shipOrder(Order order) {
       currState.shipOrder(order, this);
    }
}
