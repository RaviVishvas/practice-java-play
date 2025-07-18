package com.example.practice.lld.parkinglot;

import lombok.Data;

@Data
public class Vehicle {

    String vehicleNumber;
    VehicleType type;

    public Vehicle(String vehicleNumber, VehicleType type) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }
}
