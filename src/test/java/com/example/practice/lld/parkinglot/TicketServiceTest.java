package com.example.practice.lld.parkinglot;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {

    @Test
    public void testCalculatePrice() {
        TicketService ticketService = new TicketService();
        Vehicle vehicle = new Vehicle("1234", VehicleType.FOUR_WHEELER);
        ParkingSpot spot = new ParkingSpot("S1", true, VehicleType.FOUR_WHEELER);
        
        // 2 hours and 30 minutes ago
        LocalDateTime entryTime = LocalDateTime.now().minusHours(2).minusMinutes(30);
        Ticket ticket = ticketService.getTicket(vehicle, spot, entryTime);
        
        BigDecimal price = ticketService.validateTicketAndCalculatePrice(ticket);
        
        // Four wheeler charge is 20. 
        // 2 hours 30 mins should be rounded to 3 hours.
        // 20 * 3 = 60
        assertEquals(BigDecimal.valueOf(60), price);
    }

    @Test
    public void testCalculatePriceMinimumCharge() {
        TicketService ticketService = new TicketService();
        Vehicle vehicle = new Vehicle("1234", VehicleType.TOW_WHEELER);
        ParkingSpot spot = new ParkingSpot("S1", true, VehicleType.TOW_WHEELER);
        
        // 5 minutes ago
        LocalDateTime entryTime = LocalDateTime.now().minusMinutes(5);
        Ticket ticket = ticketService.getTicket(vehicle, spot, entryTime);
        
        BigDecimal price = ticketService.validateTicketAndCalculatePrice(ticket);
        
        // Two wheeler charge is 10.
        // 5 mins should be rounded to 1 hour.
        // 10 * 1 = 10
        assertEquals(BigDecimal.valueOf(10), price);
    }

    @Test
    public void testCalculateTotalEarnings() {
        TicketService ticketService = new TicketService();
        java.time.LocalDate today = java.time.LocalDate.now();
        
        // Ticket 1: 1 hour ago
        Vehicle v1 = new Vehicle("V1", VehicleType.TOW_WHEELER);
        ParkingSpot s1 = new ParkingSpot("S1", true, VehicleType.TOW_WHEELER);
        Ticket t1 = ticketService.getTicket(v1, s1, LocalDateTime.now().minusHours(1));
        ticketService.validateTicketAndCalculatePrice(t1); // Should be 10
        
        // Ticket 2: 2 hours ago
        Vehicle v2 = new Vehicle("V2", VehicleType.FOUR_WHEELER);
        ParkingSpot s2 = new ParkingSpot("S2", true, VehicleType.FOUR_WHEELER);
        Ticket t2 = ticketService.getTicket(v2, s2, LocalDateTime.now().minusHours(2));
        ticketService.validateTicketAndCalculatePrice(t2); // Should be 40
        
        BigDecimal totalEarnings = ticketService.calculateTotalEarnings(today);
        assertEquals(BigDecimal.valueOf(50), totalEarnings);
    }
}
