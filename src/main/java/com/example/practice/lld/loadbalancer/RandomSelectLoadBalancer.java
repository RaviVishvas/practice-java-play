package com.example.practice.lld.loadbalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomSelectLoadBalancer extends LoadBalancer{

    @Override
    public Server getDestination(Request request) {
        List<Server> servers = new ArrayList<>(getDestinations(request));

        if(servers.isEmpty()){
            System.out.println("Please provide correct service name...");
            throw new IllegalArgumentException("Please provide correct service name...");
        }

        int randomInt = new Random().nextInt();
        return servers.get(randomInt%servers.size());
    }

}
