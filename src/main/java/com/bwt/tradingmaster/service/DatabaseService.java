package com.bwt.tradingmaster.service;

import com.bwt.tradingmaster.model.AdminRole;
import com.bwt.tradingmaster.model.AdminUser;
import com.bwt.tradingmaster.model.AdminUserStatus;
import com.bwt.tradingmaster.repository.AdminUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class DatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initDatabase() {
        logger.info("数据库初始化完成");

        // 检查是否需要初始化默认管理员
        if (adminUserRepository.count() == 0) {
            createDefaultAdmin();
        }
    }

    private void createDefaultAdmin() {
        AdminUser admin = new AdminUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));  // 加密密码
        admin.setEmail("admin@admin.com");
        admin.setMobile("+1(502) 414-5497");
        admin.setRole(AdminRole.SUPER);  // 使用 AdminRole 枚举设置角色
        admin.setStatus(AdminUserStatus.NORMAL);  // 使用 AdminUserStatus 枚举设置状态
        admin.setCreatedTime(LocalDateTime.now());
        adminUserRepository.save(admin);
        logger.info("默认管理员已创建: " + admin.getUsername());
    }


}
