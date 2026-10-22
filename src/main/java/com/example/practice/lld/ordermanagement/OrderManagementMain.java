package com.example.practice.lld.ordermanagement;

import com.example.practice.lld.ordermanagement.enums.PaymentMode;
import com.example.practice.lld.ordermanagement.model.*;
import com.example.practice.lld.ordermanagement.service.*;
import com.example.practice.lld.ordermanagement.state.CartState;
import com.example.practice.lld.ordermanagement.state.State;
import com.example.practice.lld.ordermanagement.strategy.CardPaymentProcessor;
import com.example.practice.lld.ordermanagement.strategy.UPIPaymentProcessor;

public class OrderManagementMain {
    public static void main(String[] args) {
        // 1. Setup Warehouse and Inventory
        WarehouseManager warehouseManager = new WarehouseManager();


        Product laptop = new Product("P1", "Laptop", 1200.0);
        Product phone = new Product("P2", "Phone", 800.0);

        Inventory inventory1 = new Inventory();
        Warehouse warehouse1 = new Warehouse("W1", inventory1, new Address("123 Street", "San Jose", "CA", "95112"));
        warehouseManager.addWarehouse(warehouse1);
        warehouseManager.updateInventory(warehouse1, laptop, 10);
        warehouseManager.updateInventory(warehouse1, phone, 20);

        Inventory inventory2 = new Inventory();
        Warehouse warehouse2 = new Warehouse("W2", inventory2, new Address("456 Avenue", "New York", "NY", "10001"));
        warehouseManager.addWarehouse(warehouse2);
        warehouseManager.updateInventory(warehouse2, phone, 17);


        // 2. Setup Cart and Order Managers
        CartManager cartManager = new CartManager();
        OrderManager orderManager = new OrderManager(warehouseManager, cartManager, new CartState());

        // 3. Setup User
        User user = new User("U1", "John Doe", new Address("Home St", "City", "ST", "12345"));

        // 4. Shopping Experience
        System.out.println("--- User adds items to cart ---");
        orderManager.addItemsInCart(user, phone, 20);

        // 5. Place Order
        System.out.println("\n--- Placing Order ---");
        Order order = orderManager.placeOrder(user, user.getAddress());

        if (order != null) {
            // 6. Payment
            System.out.println("\n--- Making Payment ---");
            orderManager.processPayment(order, PaymentMode.CARD);

            // 7. Shipping
            System.out.println("\n--- Shipping Order ---");
            orderManager.shipOrder(order);
        }

        System.out.println(" Current State ---> "+ orderManager.getCurrState().toString());

        System.out.println("Remaining items ===> "+ orderManager.getWarehouseManager().selectWarehouse(phone, 10));
        System.out.println("Remaining items ===> "+ orderManager.getWarehouseManager().selectWarehouse(laptop, 10));

        // 8. Attempting to place an order that exceeds stock
        System.out.println("\n--- Attempting to place an order exceeding stock ---");
        orderManager.addItemsInCart(user, laptop, 20); // Only 10 available in W1, 0 in W2
        orderManager.placeOrder(user, user.getAddress());

        // 9. Multi-warehouse check (Phone from W2)
        System.out.println("\n--- Placing order for items in different warehouse (Phone from W2) ---");
        orderManager.addItemsInCart(user, phone, 15); // 15 available in W2 (20 initially, none taken by first order)
        Order order2 = orderManager.placeOrder(user, user.getAddress());
        if (order2 != null) {
            System.out.println("Order 2 fulfilled from: " + order2.getFulfilledFrom().getWarehouseId());
            orderManager.processPayment(order2, PaymentMode.UPI);

            orderManager.shipOrder(order2);
        }

        System.out.println("Current State ---> "+ orderManager.getCurrState().toString());
    }
}
