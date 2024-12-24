package com.example.demo.util.caching;

import java.util.concurrent.TimeUnit;

public class CacheValueWithTimestamp {
    private final Object value;
    private final long expiryTime;

    public CacheValueWithTimestamp(Object value, long ttl, TimeUnit timeUnit) {
        this.value = value;
        this.expiryTime = System.nanoTime() + timeUnit.toNanos(ttl);
    }

    public boolean isExpired() {
        return System.nanoTime() > expiryTime;
    }

    public Object getValue() {
        return value;
    }
}
