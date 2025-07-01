package com.example.practice.parkinglot;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;


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
