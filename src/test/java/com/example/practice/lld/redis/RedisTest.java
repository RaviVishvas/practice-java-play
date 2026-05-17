package com.example.practice.lld.redis;

import com.example.practice.lld.redis.command.CommandInvoker;
import com.example.practice.lld.redis.storage.RedisStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedisTest {
    private RedisStore store;
    private CommandInvoker invoker;

    @BeforeEach
    public void setUp() {
        store = RedisStore.getInstance();
        // Clear store for each test (using DEL manually since we don't have FLUSHALL)
        for (String key : store.keys()) {
            store.delete(key);
        }
        invoker = new CommandInvoker(store);
    }

    @Test
    public void testStringOperations() {
        assertEquals("OK", invoker.invoke("SET name Ravi"));
        assertEquals("Ravi", invoker.invoke("GET name"));
        assertEquals(1, invoker.invoke("DEL name"));
        assertEquals("(nil)", invoker.invoke("GET name"));
    }

    @Test
    public void testHashOperations() {
        assertEquals(1, invoker.invoke("HSET user:1 name Ravi"));
        assertEquals(1, invoker.invoke("HSET user:1 age 30"));
        assertEquals("Ravi", invoker.invoke("HGET user:1 name"));
        assertEquals("30", invoker.invoke("HGET user:1 age"));
        assertEquals("WRONGTYPE Operation against a key holding the wrong kind of value", invoker.invoke("GET user:1"));
    }

    @Test
    public void testListOperations() {
        assertEquals(1, invoker.invoke("LPUSH tasks task1"));
        assertEquals(2, invoker.invoke("LPUSH tasks task2"));
        assertEquals("task2", invoker.invoke("LPOP tasks"));
        assertEquals("task1", invoker.invoke("LPOP tasks"));
        assertEquals("(nil)", invoker.invoke("LPOP tasks"));
    }

    @Test
    public void testSetOperations() {
        assertEquals(1, invoker.invoke("SADD tags java"));
        assertEquals(1, invoker.invoke("SADD tags redis"));
        assertEquals(0, invoker.invoke("SADD tags java"));
        Set<String> members = (Set<String>) invoker.invoke("SMEMBERS tags");
        assertTrue(members.contains("java"));
        assertTrue(members.contains("redis"));
        assertEquals(2, members.size());
    }

    @Test
    public void testExpiration() throws InterruptedException {
        invoker.invoke("SET temp val EX 1");
        assertEquals("val", invoker.invoke("GET temp"));
        Thread.sleep(1100);
        assertEquals("(nil)", invoker.invoke("GET temp"));
    }
}
