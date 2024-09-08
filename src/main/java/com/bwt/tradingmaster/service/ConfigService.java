package com.bwt.tradingmaster.service;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    public void initConfig(String configPath) {
        // 模拟配置文件读取和初始化逻辑
        System.out.println("配置文件已加载: " + configPath);
    }
}
