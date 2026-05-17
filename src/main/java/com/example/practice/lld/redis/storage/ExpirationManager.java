package com.example.practice.lld.redis.storage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpirationManager {
    private final RedisStore store;
    private final ScheduledExecutorService scheduler;

    public ExpirationManager(RedisStore store) {
        this.store = store;
        this.scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "ExpirationManagerThread");
            thread.setDaemon(true);
            return thread;
        });
    }

    public void start() {
        // Run cleanup every 10 seconds
        scheduler.scheduleAtFixedRate(this::cleanup, 10, 10, TimeUnit.SECONDS);
    }

    private void cleanup() {
        // This is a simplified active cleanup. 
        // In real Redis, this would be more sophisticated (sampling).
        store.keys(); // Calling keys() triggers cleanup in our current implementation
    }

    public void stop() {
        scheduler.shutdown();
    }
}
