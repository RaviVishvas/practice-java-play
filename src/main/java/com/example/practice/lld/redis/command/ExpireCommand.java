package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;

public class ExpireCommand implements RedisCommand {
    private final RedisStore store;

    public ExpireCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length != 2) {
            return "ERR wrong number of arguments for 'expire' command";
        }
        String key = args[0];
        long seconds;
        try {
            seconds = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            return "ERR value is not an integer or out of range";
        }

        RedisValue redisValue = store.get(key);
        if (redisValue == null) {
            return 0;
        }

        long expiryTime = System.currentTimeMillis() + (seconds * 1000);
        store.set(key, new RedisValue(redisValue.getValue(), redisValue.getType(), expiryTime));
        return 1;
    }
}
