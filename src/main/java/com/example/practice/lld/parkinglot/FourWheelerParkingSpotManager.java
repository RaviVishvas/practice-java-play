package com.example.practice.lld.parkinglot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FourWheelerParkingSpotManager implements ParkingSpotManager {
    private List<ParkingSpot> parkingSpots;

    public FourWheelerParkingSpotManager() {
        this.parkingSpots = new ArrayList<>();
    }

    @Override
    public List<ParkingSpot> getAvailableSpot() {
        return parkingSpots.stream().filter(ParkingSpot::isAvailable).toList();
    }

    @Override
    public void parkVehicle(ParkingSpot spot) {
        spot.setAvailable(false);
    }

    @Override
    public void removeVehicle(ParkingSpot spot) {
        spot.setAvailable(true);
    }

    @Override
    public boolean addSpot(ParkingSpot spot) {
        return parkingSpots.add(spot);
    }

    @Override
    public ParkingSpot removeSpot(ParkingSpot spot) {
        return null;
    }

}
