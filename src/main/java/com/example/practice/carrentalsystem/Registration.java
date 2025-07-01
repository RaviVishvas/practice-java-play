package com.example.practice.carrentalsystem;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Data
public class Registration {

    private String registrationId;
    private Vehicle vehicle;
    private String startTime;
    private String endTime;
    private RegistrationStatus regStatus;
    private BigDecimal amountPerHour;
    private String userId;

}
