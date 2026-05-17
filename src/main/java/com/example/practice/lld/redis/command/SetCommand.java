package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.DataType;
import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;

public class SetCommand implements RedisCommand {
    private final RedisStore store;

    public SetCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length < 2) {
            return "ERR wrong number of arguments for 'set' command";
        }
        String key = args[0];
        String value = args[1];
        Long expiry = null;

        if (args.length >= 4 && "EX".equalsIgnoreCase(args[2])) {
            try {
                long seconds = Long.parseLong(args[3]);
                expiry = System.currentTimeMillis() + (seconds * 1000);
            } catch (NumberFormatException e) {
                return "ERR value is not an integer or out of range";
            }
        }

        store.set(key, new RedisValue(value, DataType.STRING, expiry));
        return "OK";
    }
}
