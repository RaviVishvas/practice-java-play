package com.example.practice.lld.loadbalancer;

public class LoadBalancerFactory {

    public LoadBalancer getLoadBalancer(LoadBalancerType type){
        return switch (type){
            case ROUND_ROBIN -> new RoundRobinLoadBalancer();
            case ROUTED -> new RoutedLoadBalancer();
            case RANDOM -> new RandomSelectLoadBalancer();
            default -> new LeastConnectionLoadBalancer();
        };
    }
}
