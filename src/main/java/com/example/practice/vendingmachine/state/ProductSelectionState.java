package com.example.practice.vendingmachine.state;

import com.example.practice.vendingmachine.Inventory;
import com.example.practice.vendingmachine.Item;
import com.example.practice.vendingmachine.VendingMachine;

import java.math.BigDecimal;

public class ProductSelectionState implements MachineState{

    private VendingMachine machine;

    public ProductSelectionState(VendingMachine machine) {
        System.out.println("Product selection State...");
        this.machine = machine;
    }

    @Override
    public void addProduct(Item item, VendingMachine machine) {
        System.out.println("You are at selection state, please select product");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("you are already at product selection stage!");
    }

    @Override
    public void chooseItem(int itemId, int quantity) {
        Inventory inventory = machine.getInventory();
        if(inventory.getProducts().containsKey(itemId)) {
            Item selectedItem = inventory.getProducts().get(itemId);

            if(selectedItem.getQuantity()<quantity){
                System.out.println("Requested quantity id more than available...., returning");
                exit();
                return;
            }

            machine.setSelectedItem(new Item(selectedItem.getName(), selectedItem.getItemId(), selectedItem.getPrice(), quantity*(-1)));

            machine.setCurrentState(new PaymentState(machine));
        } else System.out.println("Selected item not available in stock!");
    }

    @Override
    public void exit() {
        machine.setCurrentState(new IdelState(machine));
    }

    @Override
    public void pay(BigDecimal amount) {

    }

    @Override
    public void dispatch() {

    }

    @Override
    public BigDecimal refundAmount(BigDecimal amount) {
        return null;
    }

    @Override
    public BigDecimal getChange(BigDecimal change) {
        return null;
    }
}
