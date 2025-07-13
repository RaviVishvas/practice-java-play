package com.example.practice.lld.vendingmachine.state;

import com.example.practice.lld.vendingmachine.VendingMachine;
import com.example.practice.lld.vendingmachine.Item;

import java.math.BigDecimal;

public class PaymentState implements MachineState{

    private VendingMachine machine;

    public PaymentState(VendingMachine machine) {
        System.out.println("Machine is in Payment state...");
        this.machine = machine;
    }

    @Override
    public void addProduct(Item item, VendingMachine machine) {
        System.out.println("please go to idle state by exit");
    }

    @Override
    public void selectProduct(VendingMachine machine) {

    }

    @Override
    public void chooseItem(int itemId, int quantity) {

    }

    @Override
    public void exit() {
      if(machine.getHasCash().compareTo(BigDecimal.ZERO)==1){
          machine.setHasCash(BigDecimal.ZERO);
          machine.setSelectedItem(null);
          System.out.println("Refunded full amount : "+ machine.getHasCash());
      }

      machine.setCurrentState(new IdelState(machine));
    }

    @Override
    public void pay(BigDecimal amount) {
        machine.setHasCash(amount);

        Item selectedItem = machine.getSelectedItem();

        BigDecimal requiredAmount = selectedItem.getPrice().multiply(BigDecimal.valueOf((long)selectedItem.getQuantity()));

        BigDecimal remainingAmt = amount.add(requiredAmount);

        if(remainingAmt.compareTo(BigDecimal.ZERO)<0){
            System.out.println("Given amount is not enough, please try again");
            refundAmount(amount);
        } else{
            getChange(remainingAmt);
            machine.setCurrentState(new DispatchingState(machine));

            machine.getCurrentState().dispatch();
        }
    }

    @Override
    public void dispatch() {
        System.out.println("please first pay required amount !");
    }

    @Override
    public BigDecimal refundAmount(BigDecimal amount) {

        System.out.println("Refunded full amount : "+ amount);
        machine.setHasCash(BigDecimal.ZERO);

        machine.setSelectedItem(null);
        machine.setCurrentState(new IdelState(machine));
        return amount;
    }

    @Override
    public BigDecimal getChange(BigDecimal change) {
        System.out.println("Remaining amount is : "+ change);
        machine.setHasCash(BigDecimal.ZERO);
        return change;
    }


}
