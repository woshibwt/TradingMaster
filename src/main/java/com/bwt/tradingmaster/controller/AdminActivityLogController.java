package com.bwt.tradingmaster.controller;

import com.bwt.tradingmaster.service.AdminActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-logs")
public class AdminActivityLogController {

    @Autowired
    private AdminActivityLogService service;

    @PostMapping("/create")
    public String createLog(@RequestParam Long userId,
                            @RequestParam String actionType,
                            @RequestParam String reqUri,
                            @RequestParam String details,
                            @RequestParam String ip) {
        service.createLog(userId, actionType, reqUri, details, ip);
        return "Log created successfully";
    }
}
