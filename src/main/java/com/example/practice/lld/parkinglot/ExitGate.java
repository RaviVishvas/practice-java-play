package com.example.practice.lld.parkinglot;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class ExitGate {

    private final SpotMangerFactory spotMangerFactory;
    private final TicketService ticketService;

    public Ticket deallocateParking(Ticket ticket) {

        BigDecimal amount = ticketService.validateTicketAndCalculatePrice(ticket);
        ticket.setFinalAmount(amount);   //history
        System.out.println("Amount to be paid: " + amount);

        ParkingSpot spot = ticket.getSpot();

        Vehicle vehicle = ticket.getVehicle();

        //do amount calculation & payment
        spotMangerFactory.getSpotManager(vehicle.getType()).removeSpot(spot);
        return ticket;
    }


}
