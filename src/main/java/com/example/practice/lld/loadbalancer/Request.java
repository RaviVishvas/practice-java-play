package com.example.practice.lld.loadbalancer;

import lombok.Data;

import java.util.Map;

@Data
public class Request {
    private String requestId;
    private String seviceName;
    private Map<String, Object> data;

    public Request(String requestId, String seviceName) {
        this.requestId = requestId;
        this.seviceName = seviceName;
    }
}
