package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.storage.RedisStore;

public class KeysCommand implements RedisCommand {
    private final RedisStore store;

    public KeysCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length != 1 || !"*".equals(args[0])) {
             return "ERR only 'KEYS *' is supported in this version";
        }
        return store.keys();
    }
}
