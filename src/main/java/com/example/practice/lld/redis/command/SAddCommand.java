package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.DataType;
import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;
import java.util.HashSet;
import java.util.Set;

public class SAddCommand implements RedisCommand {
    private final RedisStore store;

    public SAddCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length < 2) {
            return "ERR wrong number of arguments for 'sadd' command";
        }
        String key = args[0];
        RedisValue redisValue = store.get(key);
        Set<String> set;

        if (redisValue == null) {
            set = new HashSet<>();
            store.set(key, new RedisValue(set, DataType.SET));
        } else if (redisValue.getType() != DataType.SET) {
            return "WRONGTYPE Operation against a key holding the wrong kind of value";
        } else {
            set = (Set<String>) redisValue.getValue();
        }

        int count = 0;
        for (int i = 1; i < args.length; i++) {
            if (set.add(args[i])) {
                count++;
            }
        }
        return count;
    }
}
