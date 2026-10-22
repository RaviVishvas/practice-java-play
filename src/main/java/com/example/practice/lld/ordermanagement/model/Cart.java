package com.example.practice.lld.ordermanagement.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private String userId;
    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        cartItems.add(new CartItem(product, quantity));
    }

    public void removeItem(Product product) {
        cartItems.removeIf(item -> item.getProduct().getProductId().equals(product.getProductId()));
    }

    public void clearCart() {
        cartItems.clear();
    }
}
