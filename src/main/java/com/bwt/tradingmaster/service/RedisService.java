package com.bwt.tradingmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void initRedis() {
        // 模拟 Redis 初始化
        System.out.println("Redis 初始化完成");
    }

    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
