package com.example.practice.lld.carrentalsystem;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class Vehicle {
    private VehicleType type;
    private String vehicleId;
    protected BigInteger cc;
    private Status status;
    protected boolean available = true;

    public Vehicle(VehicleType type, String vehicleId, BigInteger cc, Status status) {
        this.type = type;
        this.vehicleId = vehicleId;
        this.cc = cc;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
