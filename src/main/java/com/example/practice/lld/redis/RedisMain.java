package com.example.practice.lld.redis;

import com.example.practice.lld.redis.command.CommandInvoker;
import com.example.practice.lld.redis.storage.ExpirationManager;
import com.example.practice.lld.redis.storage.RedisStore;
import java.util.Scanner;

public class RedisMain {
    public static void main(String[] args) {
        RedisStore store = RedisStore.getInstance();
        ExpirationManager expirationManager = new ExpirationManager(store);
        expirationManager.start();

        CommandInvoker invoker = new CommandInvoker(store);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simple Redis-like Store started. Type 'EXIT' to quit.");
        
        while (true) {
            System.out.print("redis> ");
            String input = scanner.nextLine();
            if ("EXIT".equalsIgnoreCase(input.trim())) {
                break;
            }
            if (input.trim().isEmpty()) continue;

            Object result = invoker.invoke(input);
            System.out.println(result);
        }
        
        scanner.close();
    }
}
