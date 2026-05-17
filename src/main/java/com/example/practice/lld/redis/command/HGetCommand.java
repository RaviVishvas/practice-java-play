package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.DataType;
import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;
import java.util.Map;

public class HGetCommand implements RedisCommand {
    private final RedisStore store;

    public HGetCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length != 2) {
            return "ERR wrong number of arguments for 'hget' command";
        }
        String key = args[0];
        String field = args[1];

        RedisValue redisValue = store.get(key);
        if (redisValue == null) return "(nil)";
        if (redisValue.getType() != DataType.HASH) {
            return "WRONGTYPE Operation against a key holding the wrong kind of value";
        }

        Map<String, String> hash = (Map<String, String>) redisValue.getValue();
        String value = hash.get(field);
        return value == null ? "(nil)" : value;
    }
}
