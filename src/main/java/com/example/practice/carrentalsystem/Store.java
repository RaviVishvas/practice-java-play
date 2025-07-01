package com.example.practice.carrentalsystem;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
public class Store {

    private String storeId;
    private String storeName;
    private Location location;
    protected List<Vehicle> vehicles;
    protected List<Registration> registrationList;

    public Store(String storeId, String storeName, Location location) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.location = location;
        this.vehicles = new ArrayList<>();
        this.registrationList = new ArrayList<>();
    }

    public boolean addVehicle(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }

    public boolean addRegistration(Registration registration) {
        return registrationList.add(registration);
    }

    public Registration createRegistration(Vehicle vehicle, Registration reg){
        reg.setVehicle(vehicle);
        registrationList.add(reg);
        return reg;
    }
}
