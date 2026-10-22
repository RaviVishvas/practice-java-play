package com.example.practice.lld.ordermanagement.model;

import com.example.practice.lld.ordermanagement.enums.OrderStatus;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private User user;
    private Address shippingAddress;
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderStatus status;
    private Warehouse fulfilledFrom;
    private double totalAmount;

    public Order(String orderId, User user, Address shippingAddress) {
        this.orderId = orderId;
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.status = OrderStatus.CREATED;
    }
}
