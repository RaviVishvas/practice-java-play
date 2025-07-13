package com.example.practice.lld.vendingmachine.state;

import com.example.practice.lld.vendingmachine.VendingMachine;
import com.example.practice.lld.vendingmachine.Item;

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
