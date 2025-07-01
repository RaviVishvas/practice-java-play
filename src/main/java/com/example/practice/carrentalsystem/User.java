package com.example.practice.carrentalsystem;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private String userId;
    private String name;
    private String mobile;
    private String address;
    private String emailId;
    private List<Registration> registrations;

    User(String userId, String name, String mob, String emailId, String add){
        this.userId = userId;
        this.name = name;
        this.mobile = mob;
        this.emailId = emailId;
        this.registrations = new ArrayList<>();
    }

}
