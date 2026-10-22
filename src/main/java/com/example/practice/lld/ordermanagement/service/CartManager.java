package com.example.practice.lld.ordermanagement.service;

import com.example.practice.lld.ordermanagement.model.Cart;
import com.example.practice.lld.ordermanagement.model.Product;
import com.example.practice.lld.ordermanagement.model.User;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private final Map<String, Cart> userCartMap = new HashMap<>();

    public Cart getCart(String userId) {
        return userCartMap.computeIfAbsent(userId, k -> {
            Cart cart = new Cart();
            cart.setUserId(k);
            return cart;
        });
    }

    public Cart addIntoCart(User user, Map<Product, Integer> items){
        Cart userCart = getCart(user.getUserId());

        for(Map.Entry<Product, Integer> entry : items.entrySet()){
            userCart.addItem(entry.getKey(), entry.getValue());
        }

        return userCart;
    }
}
