package com.example.practice.lld.carrentalsystem;

import com.example.practice.lld.carrentalsystem.enums.City;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private String address;
    private City city;
    private String state;
    private String pincode;
}