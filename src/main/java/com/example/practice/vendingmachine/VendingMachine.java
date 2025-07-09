package com.example.practice.vendingmachine;

import com.example.practice.vendingmachine.state.IdelState;
import com.example.practice.vendingmachine.state.MachineState;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VendingMachine {

    private MachineState currentState;
    private Inventory inventory;
    private Item selectedItem;
    private BigDecimal hasCash;

    VendingMachine(){
        currentState = new IdelState();
        inventory = new Inventory();
    }

    public void addProduct(String name, BigDecimal price, int count){

        currentState.addProduct(new Item(name, 1, price, count), this);
    }


}
