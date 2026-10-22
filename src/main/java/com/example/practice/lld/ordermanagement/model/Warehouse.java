package com.example.practice.lld.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    private String warehouseId;
    private Inventory inventory;
    private Address address;
}
