package com.example.practice.lld.redis.command;

import com.example.practice.lld.redis.storage.RedisStore;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, RedisCommand> commands;

    public CommandInvoker(RedisStore store) {
        this.commands = new HashMap<>();
        commands.put("SET", new SetCommand(store));
        commands.put("GET", new GetCommand(store));
        commands.put("DEL", new DelCommand(store));
        commands.put("KEYS", new KeysCommand(store));
        commands.put("HSET", new HSetCommand(store));
        commands.put("HGET", new HGetCommand(store));
        commands.put("LPUSH", new LPushCommand(store));
        commands.put("LPOP", new LPopCommand(store));
        commands.put("SADD", new SAddCommand(store));
        commands.put("SMEMBERS", new SMembersCommand(store));
        commands.put("EXPIRE", new ExpireCommand(store));
    }

    public Object invoke(String input) {
        String[] parts = input.trim().split("\\s+");
        if (parts.length == 0) return null;

        String commandName = parts[0].toUpperCase();
        RedisCommand command = commands.get(commandName);

        if (command == null) {
            return "ERR unknown command '" + commandName + "'";
        }

        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, parts.length - 1);

        return command.execute(args);
    }
}
