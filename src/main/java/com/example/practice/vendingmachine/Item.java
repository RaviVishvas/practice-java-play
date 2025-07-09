package com.example.practice.vendingmachine;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Item {

    private String name;
    private Integer itemId;

    private BigDecimal price;

    private Integer quantity;


    public Item(String name, Integer itemId, BigDecimal price, Integer quantity) {
        this.name = name;
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
    }
}
