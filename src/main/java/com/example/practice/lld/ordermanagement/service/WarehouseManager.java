package com.example.practice.lld.ordermanagement.service;

import com.example.practice.lld.ordermanagement.model.Inventory;
import com.example.practice.lld.ordermanagement.model.Product;
import com.example.practice.lld.ordermanagement.model.Warehouse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseManager {
    private Map<String, Warehouse> warehouses;

    public WarehouseManager() {
        this.warehouses = new HashMap<>();
    }

    public void addWarehouse(Warehouse warehouse) {
        if(!warehouses.containsKey(warehouse.getWarehouseId())) {
            warehouses.put(warehouse.getWarehouseId(), warehouse);
        }
    }

    /**
     * Finds a warehouse that has the required quantity of the product.
     * Simple implementation: returns the first warehouse that has sufficient stock.
     */
    public Warehouse selectWarehouse(Product product, int quantity) {
        for (Warehouse warehouse : warehouses.values()) {
            if (warehouse.getInventory().getProductCount(product) >= quantity) {
                return warehouse;
            }
        }
        return null;
    }

    public void updateInventory(Warehouse warehouse, Product product, int quantityChange) {
        if (quantityChange < 0) {
            warehouse.getInventory().removeProduct(product, Math.abs(quantityChange));
        } else {
            warehouse.getInventory().addProduct(product, quantityChange);
        }
    }

    public int availableItems(Product product){
        List<Inventory> inventories = warehouses.values().stream().map(Warehouse::getInventory).toList();

        return inventories.stream()
                .mapToInt(inv -> inv.getProductCount(product))
                .sum();
    }
}
