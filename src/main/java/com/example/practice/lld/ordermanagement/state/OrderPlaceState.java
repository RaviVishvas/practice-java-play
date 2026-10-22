package com.example.practice.lld.ordermanagement.state;

import com.example.practice.lld.ordermanagement.enums.OrderStatus;
import com.example.practice.lld.ordermanagement.model.*;
import com.example.practice.lld.ordermanagement.service.CartManager;
import com.example.practice.lld.ordermanagement.service.OrderManager;
import com.example.practice.lld.ordermanagement.service.WarehouseManager;
import com.example.practice.lld.ordermanagement.strategy.PaymentProcessor;

import java.util.UUID;

public class OrderPlaceState implements State {

    @Override
    public void addItems(User user, Product product, int quantity, OrderManager state) {
        System.out.println("Invalid State");
    }

    @Override
    public Order placeOrder(User user, Address address, OrderManager state) {
        CartManager cartManager = state.getCartManager();
        WarehouseManager warehouseManager = state.getWarehouseManager();
        Cart cart = cartManager.getCart(user.getUserId());
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Cart is empty for user: " + user.getUserName());
            return null;
        }

        Order order = new Order(UUID.randomUUID().toString(), user, address);
        double totalAmount = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            Warehouse warehouse = warehouseManager.selectWarehouse(cartItem.getProduct(), cartItem.getQuantity());
            if (warehouse == null) {
                System.out.println("Insufficient stock for product: " + cartItem.getProduct().getProductName());
                state.currState = new CartState();
                cart.clearCart();
                return null; // In a real system, we might handle partial orders or multiple warehouses
            }

            // Reserve stock
            warehouseManager.updateInventory(warehouse, cartItem.getProduct(), -cartItem.getQuantity());

            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice());
            order.getOrderItems().add(orderItem);
            order.setFulfilledFrom(warehouse); // Simplifying: one order from one warehouse for this LLD
            totalAmount += orderItem.getPriceAtOrder() * orderItem.getQuantity();
        }

        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        cart.clearCart();

        System.out.println("Order created successfully with ID: " + order.getOrderId());
        state.currState = new PaymentState();
        return order;
    }

    @Override
    public void processPayment(Order order, PaymentProcessor paymentProcessor, OrderManager state) {
        System.out.println("Invalid State");
    }

    @Override
    public void shipOrder(Order order, OrderManager state) {
        System.out.println("Invalid State");
    }
}
