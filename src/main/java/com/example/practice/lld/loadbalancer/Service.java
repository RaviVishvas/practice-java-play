package com.example.practice.lld.loadbalancer;

import java.util.HashSet;
import java.util.Set;

public class Service {

    private String name;
    private Set<Server> servers;

    public Service(String name) {
        this.name = name;
        this.servers = new HashSet<>();
    }

    public Set<Server> getServers() {
        return servers;
    }

    public void addServer(Server server){
        servers.add(server);
    }

    public void removeServer(Server server){
        servers.remove(server);
    }
}
