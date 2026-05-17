package com.example.practice.lld.redis.command;

public interface RedisCommand {
    Object execute(String[] args);
}
