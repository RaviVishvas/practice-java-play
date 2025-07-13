package com.example.practice.lld.vendingmachine.state;

import com.example.practice.lld.vendingmachine.Item;
import com.example.practice.lld.vendingmachine.VendingMachine;

import java.math.BigDecimal;

public class IdelState implements MachineState{

    VendingMachine machine;

    public IdelState() {
        System.out.println("Machine is in idle state...");
    }

    public IdelState(VendingMachine machine) {
        System.out.println("Machine is in idle state...");
        this.machine = machine;
    }

    @Override
    public void addProduct(Item item, VendingMachine machine) {
       machine.getInventory().updateItem(item, 1);
    }

    @Override
    public void selectProduct( VendingMachine machine) {
        machine.setCurrentState(new ProductSelectionState(machine));
    }

    @Override
    public void chooseItem(int itemId, int quantity) {
        System.out.println("Please first click on select product!");

    }

    @Override
    public void exit() {
        machine.setCurrentState(new IdelState());
    }


    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Please first select product");
    }

    @Override
    public void dispatch() {
        System.out.println("Please first select product and pay");
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
