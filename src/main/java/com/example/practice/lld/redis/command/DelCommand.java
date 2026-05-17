package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.storage.RedisStore;

public class DelCommand implements RedisCommand {
    private final RedisStore store;

    public DelCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length < 1) {
            return "ERR wrong number of arguments for 'del' command";
        }
        int count = 0;
        for (String key : args) {
            if (store.exists(key)) {
                store.delete(key);
                count++;
            }
        }
        return count;
    }
}
