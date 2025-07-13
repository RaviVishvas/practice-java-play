package com.example.practice.lld.parkinglot;

import java.util.List;

public interface ParkingSpotManager {
    List<ParkingSpot> getAvailableSpot();

    void parkVehicle(ParkingSpot spot);

    void removeVehicle(ParkingSpot spot);

    boolean addSpot(ParkingSpot spot);

    ParkingSpot removeSpot(ParkingSpot spot);
}
