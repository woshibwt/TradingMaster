package com.bwt.tradingmaster.service;

import org.springframework.stereotype.Service;

@Service
public class LogService {

    public void initLogs(String appName, String logPath, String logLevel, boolean consoleOutput) {
        // 模拟日志初始化
        System.out.println("日志系统初始化: " + logPath + ", 级别: " + logLevel);
    }
}
