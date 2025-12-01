package com.example.practice.lld.carrentalsystem;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Data
public class Store {

    private String storeId;
    private String storeName;
    private Location location;
    protected Map<String, Vehicle> vehicles;
    protected List<Registration> registrationList;

    public Store(String storeId, String storeName, Location location) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.location = location;
        this.vehicles = new HashMap<>();
        this.registrationList = new ArrayList<>();
    }

    public boolean addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
        return true;
    }

    public List<Vehicle> getVehiclesList() {
        return new ArrayList<>(vehicles.values());
    }


    public boolean removeVehicle(Vehicle vehicle) {
        return vehicles.remove(vehicle.getVehicleId()) != null;
    }

    public boolean addRegistration(Registration registration) {
        return registrationList.add(registration);
    }

    public Registration createRegistration(Vehicle vehicle, Registration reg) {
        reg.setVehicle(vehicle);
        registrationList.add(reg);
        return reg;
    }
}
