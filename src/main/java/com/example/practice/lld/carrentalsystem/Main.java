package com.example.practice.lld.carrentalsystem;

import java.math.BigInteger;

import static com.example.practice.lld.carrentalsystem.enums.City.INDORE;

public class Main {


    public static void main(String[] args) {
       RentalSystem rentalSystem = new RentalSystem();
       System.out.println("Rental System initialized.");

       // Create two stores in INDORE city
       Location indoreLocation1 = new Location("123, Main Street", INDORE, "MP", "452001");
       Store store1 = new Store("store1", "Indore Store 1", indoreLocation1);

       Location indoreLocation2 = new Location("456, Park Avenue", INDORE, "MP", "452002");
       Store store2 = new Store("store2", "Indore Store 2", indoreLocation2);
       System.out.println("\nTwo stores created in INDORE city.");

       // Add stores to the rental system
       rentalSystem.addStore(store1);
       rentalSystem.addStore(store2);
       System.out.println("Stores added to the rental system.");

       // Get all stores by city and print them
       System.out.println("\nGetting all stores in INDORE...");
       for (Store store : rentalSystem.getStores(INDORE)) {
           System.out.println("Store ID: " + store.getStoreId() + ", Name: " + store.getStoreName() + ", Location: " + store.getLocation().getCity());
       }

       // Add vehicles to store1
       store1.addVehicle(new Vehicle(VehicleType.CAR, "V001", new BigInteger("1500"), Status.ACTIVE));
       store1.addVehicle(new Vehicle(VehicleType.CAR, "V002", new BigInteger("1200"), Status.ACTIVE));
       store1.addVehicle(new Vehicle(VehicleType.BIKE, "B001", new BigInteger("150"), Status.ACTIVE));

       // Add vehicles to store2
       store2.addVehicle(new Vehicle(VehicleType.CAR, "V003", new BigInteger("1600"), Status.ACTIVE));
       store2.addVehicle(new Vehicle(VehicleType.BIKE, "B002", new BigInteger("200"), Status.ACTIVE));
       System.out.println("Vehicles added to stores.");

       // Create and add users to the rental system
       User userRavi = new User("user1", "Ravi", "1234567890", "ravi@example.com", "Indore");
       User userRitesh = new User("user2", "Ritesh", "0987654321", "ritesh@example.com", "Indore");

       rentalSystem.addUser(userRavi);
       rentalSystem.addUser(userRitesh);
       System.out.println("Users 'Ravi' and 'Ritesh' added to the rental system.");

       // Book a CAR from indoreStore1 for Ravi user
       System.out.println("\nAttempting to book a car for Ravi from Indore Store 1...");
       Registration raviBooking = rentalSystem.bookVehicle(userRavi.getUserId(), INDORE, store1.getStoreId(), "V001");

       if (raviBooking != null) {
           System.out.println("Booking successful for Ravi: " + raviBooking.getRegStatus() + " for Vehicle " + raviBooking.getVehicle().getVehicleId());
       } else {
           System.out.println("Booking failed for Ravi.");
       }



    }

    public static String getPageString(int totalPage, int currPage){

        String pageStr = "";
        int totalCount = 3;
        if(currPage==1){

            while(currPage<=totalPage && currPage<=3){
                pageStr+=currPage;
                currPage++;
            }

            if(currPage<totalPage) pageStr+="..."+totalPage;

        } else if(currPage == totalPage){

            while(currPage>0 && currPage > totalPage-totalCount){
                pageStr=currPage+pageStr;
                currPage--;
            }

            if(currPage>0) pageStr="..."+pageStr;
        } else {
            pageStr = (currPage-1)+""+currPage+""+(currPage+1);
            if(currPage-1>1) pageStr = "1"+pageStr;
            else if(currPage-1>2) pageStr = "1..."+pageStr;
            else if(currPage+1 <totalPage) pageStr+= totalPage;
            else pageStr+="..."+totalPage;
        }

        return pageStr;
    }
}
