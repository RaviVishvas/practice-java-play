package com.example.practice.lld.loadbalancer;

import lombok.Data;

@Data
public class Server {

    private String ip;
    private int requestBeingServed;
    private int threshold;

    public Server(String ip, int requestBeingServed, int threshold) {
        this.ip = ip;
        this.requestBeingServed = requestBeingServed;
        this.threshold = threshold;
    }

    public boolean accept(Request request){
        if(requestBeingServed<threshold){
            requestBeingServed++;
            return true;
        } else {
            return false;
        }
    }

    public boolean served(Request request){
        System.out.println("Request is served...");
        requestBeingServed--;

        return true;
    }
}
