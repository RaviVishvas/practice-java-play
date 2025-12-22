package com.example.practice.lld.carrentalsystem;

import com.example.practice.lld.carrentalsystem.enums.City;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class RentalSystem {

    private Map<String, User> users;
    private Map<City, Map<String, Store>> stores;

    public RentalSystem() {
        this.users = new HashMap<>();
        this.stores = new HashMap<>();
    }

    public boolean addUser(User user) {
        users.put(user.getUserId(), user);
        return true;
    }

    public boolean addStore(Store store) {
        stores.computeIfAbsent(store.getLocation().getCity(), k -> new HashMap<>()).put(store.getStoreId(), store);
        return true;
    }

    public List<Store> getStores(City city) {
        return new ArrayList<>(stores.getOrDefault(city, Collections.emptyMap()).values());
    }

    public Registration bookVehicle(String userId, City city, String storeId, String vehicleNo){
        User user = users.containsKey(userId) ? users.get(userId) : null;

        if (Objects.isNull(user)) {
            log.error("User not found.");
            return null;
        }

        Map<String, Store> cityStores = stores.get(city);

        if (Objects.isNull(cityStores) || cityStores.isEmpty()) {
            log.error("Store not found for the given city.");
            return null;
        }

        Store userStore = cityStores.getOrDefault(storeId, null);

        if (userStore == null) {
            log.error("Store not found for the given city.");
            return null;
        }

        Vehicle vehicle = userStore.getVehicles().get(vehicleNo);
        if (vehicle == null || !vehicle.isAvailable()) {
            log.error("Vehicle not available.");
            return null;
        }

        Registration reg = new Registration();
        reg.setVehicle(vehicle);
        vehicle.setAvailable(false);
        reg.setRegStatus(RegistrationStatus.BOOKED);
        reg.setUserId(user.getUserId());
        user.getRegistrations().add(reg);
        userStore.addRegistration(reg);

        return reg;
    }

    public List<Vehicle> getVehiclesByStoreId(City city, String storeId) {
        Map<String, Store> cityStore = stores.getOrDefault(city, null);

        if(Objects.nonNull(cityStore)){
            Store store = cityStore.getOrDefault(storeId, null);

            if (Objects.nonNull(store)){
                return store.getVehiclesList();
            }
        }
        return Collections.emptyList();
    }
}
