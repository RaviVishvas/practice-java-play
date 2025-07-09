package com.example.practice.vendingmachine;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineMain {

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        System.out.println("Updating inventory !");

        for(int i=1; i<5; i++){
            machine.getInventory().updateItem(new Item("Item-"+i, i, BigDecimal.valueOf(10.00), 5), i);
        }

        System.out.println("Displaying All products....");

        System.out.println("clicking 'SELECT PRODUCT!'");
        machine.getCurrentState().selectProduct(machine);

        displayItems(machine);

        System.out.println("Choosing product....");
        machine.getCurrentState().chooseItem(3, 3);

        machine.getCurrentState().pay(BigDecimal.valueOf(20));

        System.out.println("display products ....");
        displayItems(machine);

    }

    public static void displayItems(VendingMachine machine){
        List<Item> products = machine.getInventory().getAllItems();

        products.forEach(item -> System.out.println("Item ID: " +item.getItemId()+ ", Name: "+ item.getName()+", Price: "+item.getPrice()+ ", available quantity: "+item.getQuantity()));

    }
}
