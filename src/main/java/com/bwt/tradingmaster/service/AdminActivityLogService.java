package com.bwt.tradingmaster.service;

import com.bwt.tradingmaster.model.AdminActivityLog;
import com.bwt.tradingmaster.repository.AdminActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminActivityLogService {

    @Autowired
    private AdminActivityLogRepository repository;

    public void createLog(Long userId, String actionType, String reqUri, String details, String ip) {
        AdminActivityLog log = new AdminActivityLog();
        log.setUserId(userId);
        log.setActionType(actionType);
        log.setReqUri(reqUri);
        log.setDetails(details);
        log.setIp(ip);
        repository.save(log);
    }
}
