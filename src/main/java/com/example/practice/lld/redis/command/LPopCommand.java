package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.DataType;
import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;
import java.util.LinkedList;

public class LPopCommand implements RedisCommand {
    private final RedisStore store;

    public LPopCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length != 1) {
            return "ERR wrong number of arguments for 'lpop' command";
        }
        String key = args[0];
        RedisValue redisValue = store.get(key);
        if (redisValue == null) return "(nil)";
        if (redisValue.getType() != DataType.LIST) {
            return "WRONGTYPE Operation against a key holding the wrong kind of value";
        }

        LinkedList<String> list = (LinkedList<String>) redisValue.getValue();
        if (list.isEmpty()) return "(nil)";
        return list.removeFirst();
    }
}
