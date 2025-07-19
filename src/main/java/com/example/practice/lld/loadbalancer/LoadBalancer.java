package com.example.practice.lld.loadbalancer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class LoadBalancer {

    Map<String, Service> serviceMap;

    LoadBalancer(){
        serviceMap = new HashMap<>();
    }

    protected Set<Server> getDestinations(Request request){
        if(!serviceMap.containsKey(request.getSeviceName())){
            System.out.println("Service is not present or service name is wrong..");
            return new HashSet<>();
        }

        return serviceMap.get(request.getSeviceName()).getServers();
    }

    public void addService(String name){
        Service service = new Service(name);
    }

    public void addServer(String serviceName, Server server){
        if(serviceMap.containsKey(serviceName)){
            serviceMap.get(serviceName).addServer(server);
        } else {
            Service service = new Service(serviceName);
            service.addServer(server);
            serviceMap.put(serviceName, service);
        }
    }

    public void removeServer(String serverName, Server server){
        if(serviceMap.containsKey(serverName)){
            serviceMap.get(serverName).removeServer(server);
        } else {
            System.out.println("Service name is wrong!");
        }
    }

    public abstract Server getDestination(Request request);

}
