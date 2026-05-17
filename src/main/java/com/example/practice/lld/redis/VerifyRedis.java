package com.example.practice.lld.redis;

import com.example.practice.lld.redis.command.CommandInvoker;
import com.example.practice.lld.redis.storage.RedisStore;
import java.util.Set;

public class VerifyRedis {
    public static void main(String[] args) {
        RedisStore store = RedisStore.getInstance();
        CommandInvoker invoker = new CommandInvoker(store);

        System.out.println("Testing SET/GET...");
        invoker.invoke("SET name Ravi");
        String name = (String) invoker.invoke("GET name");
        System.out.println("GET name: " + name);
        assert "Ravi".equals(name);

        System.out.println("Testing HASH...");
        invoker.invoke("HSET user:1 city Bangalore");
        String city = (String) invoker.invoke("HGET user:1 city");
        System.out.println("HGET user:1 city: " + city);
        assert "Bangalore".equals(city);

        System.out.println("Testing LIST...");
        invoker.invoke("LPUSH list item1");
        invoker.invoke("LPUSH list item2");
        String item = (String) invoker.invoke("LPOP list");
        System.out.println("LPOP list: " + item);
        assert "item2".equals(item);

        System.out.println("Testing SET...");
        invoker.invoke("SADD myset val1");
        invoker.invoke("SADD myset val2");
        Set<String> members = (Set<String>) invoker.invoke("SMEMBERS myset");
        System.out.println("SMEMBERS myset: " + members);
        assert members.size() == 2;

        System.out.println("Testing EXPIRE...");
        invoker.invoke("SET temp val EX 1");
        System.out.println("GET temp (before): " + invoker.invoke("GET temp"));
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {}
        System.out.println("GET temp (after 1.2s): " + invoker.invoke("GET temp"));
        assert "(nil)".equals(invoker.invoke("GET temp"));

        System.out.println("All tests passed!");
    }
}
