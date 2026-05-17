package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.DataType;
import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;
import java.util.HashMap;
import java.util.Map;

public class HSetCommand implements RedisCommand {
    private final RedisStore store;

    public HSetCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length < 3) {
            return "ERR wrong number of arguments for 'hset' command";
        }
        String key = args[0];
        String field = args[1];
        String value = args[2];

        RedisValue redisValue = store.get(key);
        Map<String, String> hash;

        if (redisValue == null) {
            hash = new HashMap<>();
            store.set(key, new RedisValue(hash, DataType.HASH));
        } else if (redisValue.getType() != DataType.HASH) {
            return "WRONGTYPE Operation against a key holding the wrong kind of value";
        } else {
            hash = (Map<String, String>) redisValue.getValue();
        }

        boolean isNew = !hash.containsKey(field);
        hash.put(field, value);
        return isNew ? 1 : 0;
    }
}
