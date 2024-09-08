package com.bwt.tradingmaster;

import com.bwt.tradingmaster.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling // 启用定时任务功能
public class TradingMasterApplication implements CommandLineRunner {

    @Autowired
    private ConfigService configService;

    @Autowired
    private LogService logService;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private KeepaliveService keepaliveService;

    @Autowired
    private ViewService viewService;

    public static void main(String[] args) {
        SpringApplication.run(TradingMasterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 配置初始化
        configService.initConfig("./config.toml");

        // 日志初始化
        logService.initLogs("trading_master", "./logs", "INFO", true);

        // 数据库初始化
        databaseService.initDatabase();

        // Redis 初始化
        redisService.initRedis();

        // 启动心跳保活
        keepaliveService.startKeepalive();

        // 启动视图模块
        viewService.runView();
    }
}
