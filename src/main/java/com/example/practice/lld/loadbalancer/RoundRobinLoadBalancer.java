package com.example.practice.lld.loadbalancer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RoundRobinLoadBalancer extends LoadBalancer{

    @Override
    public Server getDestination(Request request) {
        Set<Server> servers = getDestinations(request);

        if(servers.isEmpty()){
            System.out.println("Please provide correct service name...");
            throw new IllegalArgumentException("Please provide correct service name...");
        }

        Queue<Server> serverQueue = new LinkedList<>(servers);
        Server server = serverQueue.poll();
        serverQueue.add(server);
        return server;
    }
}
