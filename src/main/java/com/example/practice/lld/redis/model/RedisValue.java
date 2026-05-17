package com.example.practice.lld.redis.model;

public class RedisValue {
    private Object value;
    private DataType type;
    private Long expiryTime; // Nullable, stores epoch milliseconds

    public RedisValue(Object value, DataType type) {
        this.value = value;
        this.type = type;
        this.expiryTime = null;
    }

    public RedisValue(Object value, DataType type, Long expiryTime) {
        this.value = value;
        this.type = type;
        this.expiryTime = expiryTime;
    }

    public Object getValue() {
        return value;
    }

    public DataType getType() {
        return type;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public boolean isExpired() {
        return expiryTime != null && System.currentTimeMillis() > expiryTime;
    }
}
