package com.example.practice.lld.loadbalancer;

public class Main {

    public static void main(String[] args) {
        LoadBalancerFactory factory = new LoadBalancerFactory();

        LoadBalancer loadBalancer = factory.getLoadBalancer(LoadBalancerType.LEAST_CONNECTION);

        System.out.println("Adding servers...");
        loadBalancer.addServer("user", new Server("10.220.2.18", 0, 10));
        loadBalancer.addServer("user", new Server("10.221.2.18", 0, 1));
        loadBalancer.addServer("user", new Server("10.223.2.18", 0, 10));

        System.out.println("Giving request...");
        Request request1 = new Request("1", "user");
        Server server = loadBalancer.getDestination(request1);
        server.accept(request1);
        System.out.println("serverd by ==> "+server.getIp());
        Request request2 = new Request("2", "user");
        Server server2 = loadBalancer.getDestination(request2);
        server2.accept(request2);
        System.out.println("serverd by ==> "+server2.getIp());
        Request request3 = new Request("3", "user");
        Server server3 = loadBalancer.getDestination(request3);
        server3.accept(request3);
        System.out.println("serverd by ==> "+server3.getIp());

        server2.served(request2);

        Request request4 = new Request("3", "user");
        Server server4 = loadBalancer.getDestination(request4);
        server4.accept(request4);
        System.out.println("serverd by ==> "+server4.getIp());

    }
}
