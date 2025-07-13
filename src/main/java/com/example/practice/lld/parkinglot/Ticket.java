package com.example.practice.lld.parkinglot;

import lombok.Data;

@Data
public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private String entryTime;
    private ParkingSpot spot;

    public Ticket(String ticketId, Vehicle vehicle, String entryTime, ParkingSpot spot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.spot = spot;
    }
}
