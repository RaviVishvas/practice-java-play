package com.example.practice.lld.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Product product;
    private int quantity;
    private double priceAtOrder;
}
