package com.example.practice.lld.parkinglot;

import lombok.Data;

@Data
public class ParkingSpot {
    private String spotId;
    private boolean available;
    private VehicleType type;

    public ParkingSpot(String spotId, boolean available, VehicleType type) {
        this.spotId = spotId;
        this.available = available;
        this.type = type;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public VehicleType getType() {
        return type;
    }
}
