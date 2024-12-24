package com.example.demo.util.caching;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

public class CustomCacheManager extends ConcurrentMapCacheManager {

    private final long ttl;
    private final TimeUnit timeUnit;

    public CustomCacheManager(long ttl, TimeUnit timeUnit) {
        this.ttl = ttl;
        this.timeUnit = timeUnit;
    }

    @Override
    protected Cache createConcurrentMapCache(String name) {
        return new ConcurrentMapCache(name, new ConcurrentHashMap<Object, Object>(), false) {
            @Override
            public ValueWrapper get(Object key) {
                ValueWrapper valueWrapper = super.get(key);
                if (valueWrapper != null && valueWrapper.get() instanceof CacheValueWithTimestamp cacheValue) {
                    if (cacheValue.isExpired()) {
                        this.evict(key);
                        return null;
                    }
                    return cacheValue::getValue;
                }
                return valueWrapper;
            }

            @Override
            public void put(Object key, Object value) {
                super.put(key, new CacheValueWithTimestamp(value, ttl, timeUnit));
            }
        };
    }
}
