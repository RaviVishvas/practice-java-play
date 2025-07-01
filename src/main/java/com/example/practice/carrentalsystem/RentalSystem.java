package com.example.practice.carrentalsystem;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.example.practice.carrentalsystem.RegistrationStatus.BOOKED;

@Data
public class RentalSystem {

    private List<User> users;
    private List<Store> stores;

    public RentalSystem() {
        this.users = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public boolean addStore(Store store) {
        return stores.add(store);
    }

    public Registration bookVehicle(User user, Vehicle vehicle, Registration reg){

        vehicle.setAvailable(false);
        reg.setVehicle(vehicle);
        reg.setRegStatus(BOOKED);
        reg.setUserId(user.getUserId());
        user.getRegistrations().add(reg);
        return reg;
    }
}
