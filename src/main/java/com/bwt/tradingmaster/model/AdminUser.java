package com.bwt.tradingmaster.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "mobile", length = 100)
    private String mobile;

    @Column(name = "attempt_count", nullable = false)
    private int attemptCount = 0;

    @Column(name = "locked_at")
    private LocalDateTime lockedAt;

    @Column(name = "login_ip", length = 100)
    private String loginIp;

    @Enumerated(EnumType.STRING)  // 使用枚举类型
    @Column(name = "role", length = 20)
    private AdminRole role;

    @Enumerated(EnumType.STRING)  // 使用枚举类型
    @Column(name = "status", length = 50)
    private AdminUserStatus status;

    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = LocalDateTime.now();
    }

    public boolean comparePassword(String inputPassword) {
        // Add password hashing mechanism (e.g., BCrypt)
        return inputPassword.equals(this.password);
    }
}
