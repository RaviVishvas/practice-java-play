package com.example.practice.vendingmachine;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Inventory {

    Map<Integer, Item> products;

    Inventory() {
        products = new HashMap<>();
    }

    public void updateItem(Item item, int itemId){
        if(products.containsKey(itemId)){
            Item it = products.get(itemId);

            if(it.getPrice()==item.getPrice())it.setQuantity(it.getQuantity()+item.getQuantity());
            else {
                it.setQuantity(it.getQuantity()+item.getQuantity());
                it.setPrice(item.getPrice());
            }
        } else products.put(itemId, item);
    }

    public List<Item> getAllItems(){
        return List.copyOf(products.values());
    }
}
