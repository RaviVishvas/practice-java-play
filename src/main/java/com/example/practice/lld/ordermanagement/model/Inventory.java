package com.example.practice.lld.ordermanagement.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // Product ID to Quantity mapping
    private final Map<String, Integer> productCategoryCountMap = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        int currentCount = productCategoryCountMap.getOrDefault(product.getProductId(), 0);
        productCategoryCountMap.put(product.getProductId(), currentCount + quantity);
    }

    public void removeProduct(Product product, int quantity) {
        int currentCount = productCategoryCountMap.getOrDefault(product.getProductId(), 0);
        if (currentCount >= quantity) {
            productCategoryCountMap.put(product.getProductId(), currentCount - quantity);
        }
    }

    public int getProductCount(Product product) {
        return productCategoryCountMap.getOrDefault(product.getProductId(), 0);
    }
}
