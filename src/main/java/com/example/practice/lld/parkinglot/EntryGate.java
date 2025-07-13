package com.example.practice.lld.parkinglot;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class EntryGate {

    private final SpotMangerFactory spotMangerFactory;
    private final TicketService ticketService;

    public List<ParkingSpot> displayEmptyParkingSpot(VehicleType vehicleType){
        ParkingSpotManager spotManager = spotMangerFactory.getSpotManager(vehicleType);

        return spotManager.getAvailableSpot();
    }

    public boolean addParkingSpot(String spotId, VehicleType type){
        return spotMangerFactory.getSpotManager(type).addSpot(new ParkingSpot(spotId, true, type));
    }

    public Ticket bookParking(ParkingSpot spot, String vehicleNumber, VehicleType vehicleType){
        spot.setAvailable(false);
        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType);
        return ticketService.getTicket(vehicle, spot, LocalDate.now().toString());
    }


}
