package com.example.practice.lld.redis.storage;

import com.example.practice.lld.redis.model.RedisValue;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStore {
    private final Map<String, RedisValue> store;
    private static RedisStore instance;

    private RedisStore() {
        this.store = new ConcurrentHashMap<>();
    }

    public static synchronized RedisStore getInstance() {
        if (instance == null) {
            instance = new RedisStore();
        }
        return instance;
    }

    public void set(String key, RedisValue value) {
        store.put(key, value);
    }

    public RedisValue get(String key) {
        RedisValue value = store.get(key);
        if (value != null && value.isExpired()) {
            store.remove(key);
            return null;
        }
        return value;
    }

    public void delete(String key) {
        store.remove(key);
    }

    public Set<String> keys() {
        // Simple cleanup while iterating for KEYS (optional but helpful)
        store.entrySet().removeIf(entry -> entry.getValue().isExpired());
        return store.keySet();
    }

    public boolean exists(String key) {
        RedisValue value = store.get(key);
        if (value != null && value.isExpired()) {
            store.remove(key);
            return false;
        }
        return value != null;
    }
}
