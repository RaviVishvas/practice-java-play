package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.model.DataType;
import com.example.practice.lld.redis.model.RedisValue;
import com.example.practice.lld.redis.storage.RedisStore;

public class SMembersCommand implements RedisCommand {
    private final RedisStore store;

    public SMembersCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length != 1) {
            return "ERR wrong number of arguments for 'smembers' command";
        }
        String key = args[0];
        RedisValue redisValue = store.get(key);
        if (redisValue == null) return "[]";
        if (redisValue.getType() != DataType.SET) {
            return "WRONGTYPE Operation against a key holding the wrong kind of value";
        }

        return redisValue.getValue();
    }
}
