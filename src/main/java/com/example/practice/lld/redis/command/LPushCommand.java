package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.DataType;
import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;
import java.util.LinkedList;

public class LPushCommand implements RedisCommand {
    private final RedisStore store;

    public LPushCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length < 2) {
            return "ERR wrong number of arguments for 'lpush' command";
        }
        String key = args[0];
        RedisValue redisValue = store.get(key);
        LinkedList<String> list;

        if (redisValue == null) {
            list = new LinkedList<>();
            store.set(key, new RedisValue(list, DataType.LIST));
        } else if (redisValue.getType() != DataType.LIST) {
            return "WRONGTYPE Operation against a key holding the wrong kind of value";
        } else {
            list = (LinkedList<String>) redisValue.getValue();
        }

        for (int i = 1; i < args.length; i++) {
            list.addFirst(args[i]);
        }
        return list.size();
    }
}
