package com.example.practice.splitwise.controller.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String phoneNumber;
}
