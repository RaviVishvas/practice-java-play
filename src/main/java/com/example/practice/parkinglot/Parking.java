package com.example.practice.parkinglot;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Parking {

    public static void main(String[] args) {
        SpotMangerFactory spotMangerFactory = new SpotMangerFactory(new TwoWheelerParkingSpotManager(), new FourWheelerParkingSpotManager());
        TicketService ticketService = new TicketService();

        EntryGate entryGate = new EntryGate(spotMangerFactory, ticketService);
        ExitGate exitGate = new ExitGate(spotMangerFactory, ticketService);


        entryGate.addParkingSpot("01", VehicleType.TOW_WHEELER);
        entryGate.addParkingSpot("03", VehicleType.TOW_WHEELER);
        entryGate.addParkingSpot("02", VehicleType.FOUR_WHEELER);

        String vehicleNumber = "2495";
        VehicleType vehicleType = VehicleType.TOW_WHEELER;

        System.out.println("Vehicle comes at EntryGate with number : "+ vehicleNumber);

        List<ParkingSpot> availableSpots = entryGate.displayEmptyParkingSpot(vehicleType);

        availableSpots.forEach(e -> System.out.println(e.getSpotId() + " isAvailable? -> "+ e.isAvailable()));

        Optional<ParkingSpot> spot = availableSpots.stream().findAny();

        Ticket ticket = null;
        if(!spot.isPresent()) System.out.println("No parking is available!");
        else {
            ticket = entryGate.bookParking(spot.get(), vehicleNumber, vehicleType);
        }


        entryGate.displayEmptyParkingSpot(vehicleType).forEach(e -> System.out.println(e.getSpotId() + " isAvailable? -> "+ e.isAvailable()));

        if(Objects.nonNull(ticket))exitGate.deallocateParking(ticket);

        entryGate.displayEmptyParkingSpot(vehicleType).forEach(e -> System.out.println(e.getSpotId() + " isAvailable? -> "+ e.isAvailable()));

    }
}
