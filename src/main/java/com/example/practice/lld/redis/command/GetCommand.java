package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;

public class GetCommand implements RedisCommand {
    private final RedisStore store;

    public GetCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length != 1) {
            return "ERR wrong number of arguments for 'get' command";
        }
        RedisValue redisValue = store.get(args[0]);
        if (redisValue == null) {
            return "(nil)";
        }
        return redisValue.getValue();
    }
}
