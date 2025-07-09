package com.example.practice.vendingmachine.state;

import com.example.practice.vendingmachine.Item;
import com.example.practice.vendingmachine.VendingMachine;

import java.math.BigDecimal;

public interface MachineState {

    void addProduct(Item item, VendingMachine machine);

    void selectProduct(VendingMachine machine);

    void chooseItem(int itemId, int quantity);

    void exit();

    void pay(BigDecimal amount);

    void dispatch();

    BigDecimal refundAmount(BigDecimal amount);

    BigDecimal getChange(BigDecimal change);
}
