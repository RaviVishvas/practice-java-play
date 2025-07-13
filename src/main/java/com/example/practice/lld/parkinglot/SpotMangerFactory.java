package com.example.practice.lld.parkinglot;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SpotMangerFactory {

    private final TwoWheelerParkingSpotManager twoWheelerParkingSpotManager;
    private final FourWheelerParkingSpotManager fourWheelerParkingSpotManager;

    ParkingSpotManager getSpotManager(VehicleType vehicleType){
        return switch (vehicleType){
            case TOW_WHEELER -> twoWheelerParkingSpotManager;
            default -> fourWheelerParkingSpotManager;
        };
    }
}
