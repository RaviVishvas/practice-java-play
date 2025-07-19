package com.example.practice.lld.loadbalancer;

import java.util.Comparator;
import java.util.Set;

public class LeastConnectionLoadBalancer extends LoadBalancer {
    @Override
    public Server getDestination(Request request) {
        Set<Server> servers = getDestinations(request);

        if(servers.isEmpty()){
            System.out.println("Please provide correct service name...");
            throw new IllegalArgumentException("Please provide correct service name...");
        }

        return servers.stream().min(Comparator.comparingInt(Server::getRequestBeingServed)).orElseThrow();
    }
}
