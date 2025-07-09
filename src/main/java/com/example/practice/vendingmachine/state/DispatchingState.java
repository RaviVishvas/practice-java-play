package com.example.practice.vendingmachine.state;

import com.example.practice.vendingmachine.Item;
import com.example.practice.vendingmachine.VendingMachine;
import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;

public class DispatchingState implements MachineState{

    VendingMachine machine;

    public DispatchingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void addProduct(Item item, VendingMachine machine) {

    }

    @Override
    public void selectProduct(VendingMachine machine) {

    }

    @Override
    public void chooseItem(int itemId, int quantity) {

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
        Item selected = machine.getSelectedItem();
        System.out.println("Dispatched "+selected.getQuantity()*(-1)+ " "+ selected.getName());

        machine.getInventory().updateItem(selected, selected.getItemId());
        machine.setSelectedItem(null);

        exit();
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
