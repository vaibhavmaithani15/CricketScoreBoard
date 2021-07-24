package com.scoreboard.match.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class GuavaCachingConfig {
    @Bean
    public CacheManager getCacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager("match-cache");
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(12, TimeUnit.HOURS);
        cacheManager.setCacheBuilder(cacheBuilder);
        return cacheManager;

    }
}
