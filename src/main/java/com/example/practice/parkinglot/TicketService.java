package com.example.practice.parkinglot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private List<Ticket> tickets = new ArrayList<>();

    Ticket getTicket(Vehicle vehicle, ParkingSpot spot, String entryTime){

        Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, entryTime, spot);
        tickets.add(ticket);

        return ticket;
    }
}
