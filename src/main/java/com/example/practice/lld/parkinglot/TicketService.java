package com.example.practice.lld.parkinglot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TicketService {

    private static final Map<String, Ticket> tickets = new ConcurrentHashMap<>();
    private static final BigDecimal TWO_WHELER_CHARGE = BigDecimal.TEN;
    private static final BigDecimal FOUR_WHELER_CHARGE = BigDecimal.valueOf(20);

    Ticket getTicket(Vehicle vehicle, ParkingSpot spot, LocalDateTime entryTime) {

        Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, entryTime, spot);
        tickets.put(ticket.getTicketId(), ticket);

        return ticket;
    }

    BigDecimal validateTicketAndCalculatePrice(Ticket ticket) {
        if (!tickets.containsKey(ticket.getTicketId())) {
            System.out.println("Ticket is not valid, penalty is imposed!");
            return BigDecimal.valueOf(100);
        }

        LocalDateTime entry = ticket.getEntryTime();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(entry, now);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        System.out.println("Time spent: " + hours + " hours and " + minutes + " minutes");

        // Example calculation logic: 10 per hour (just as a placeholder)
        BigDecimal hourlyRate = ticket.getVehicle().getVehicleType() == VehicleType.TOW_WHEELER ? TWO_WHELER_CHARGE : FOUR_WHELER_CHARGE;

        // Round up to the nearest hour for charging
        long billableHours = minutes > 0 ? hours + 1 : hours;
        if (billableHours == 0) billableHours = 1; // Minimum charge of 1 hour

        BigDecimal amount = hourlyRate.multiply(BigDecimal.valueOf(billableHours));
        ticket.setFinalAmount(amount);
        return amount;
    }

    public BigDecimal calculateTotalEarnings(LocalDate date) {
        return tickets.values().stream()
                .filter(t -> t.getEntryTime().toLocalDate().equals(date))
                .map(Ticket::getFinalAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
