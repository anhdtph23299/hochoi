package com.example.demo.util.caching;

import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
//    @Bean
//    public CacheManager cacheManager() {
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager("students");
//        cacheManager.setCaffeine(Caffeine.newBuilder()
//                .expireAfterWrite(1, TimeUnit.HOURS));
//        return cacheManager;
//    }
        @Bean
        public CacheManager cacheManager() {
            return new CustomCacheManager(10, TimeUnit.SECONDS);
        }
}