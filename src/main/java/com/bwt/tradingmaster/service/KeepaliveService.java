package com.bwt.tradingmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KeepaliveService {

    @Autowired
    private RedisService redisService;

    @Scheduled(fixedRate = 5000) // 每 5 秒执行一次
    public void startKeepalive() {
        redisService.setKey("app:keepalive", "active");
        System.out.println("心跳保活信号已发送");
    }
}
