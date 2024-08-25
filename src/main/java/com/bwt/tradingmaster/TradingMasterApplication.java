package com.bwt.tradingmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TradingMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingMasterApplication.class, args);
    }

}
