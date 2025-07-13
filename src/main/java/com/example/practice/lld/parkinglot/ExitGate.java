package com.example.practice.lld.parkinglot;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ExitGate {

    private final SpotMangerFactory spotMangerFactory;
    private final TicketService ticketService;

    public Ticket deallocateParking(Ticket ticket){
        ParkingSpot spot = ticket.getSpot();

        //do amount calculation & payment
        spot.setAvailable(true);

        return ticket;
    }


}
