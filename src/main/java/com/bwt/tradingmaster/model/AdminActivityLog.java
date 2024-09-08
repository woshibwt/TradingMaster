package com.bwt.tradingmaster.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;  // 需要导入 LocalDateTime

@Entity
@Table(name = "admin_activity_log")
public class AdminActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "action_type", length = 30, nullable = false)
    private String actionType;

    @Column(name = "req_uri", length = 300, nullable = false)
    private String reqUri;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    @Column(name = "ip", length = 60, nullable = false)
    private String ip;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTime = LocalDateTime.now();

    // Getters and setters
    // Constructor, etc.
}
