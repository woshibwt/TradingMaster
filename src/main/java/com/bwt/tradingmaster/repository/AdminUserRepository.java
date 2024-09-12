package com.bwt.tradingmaster.repository;

import com.bwt.tradingmaster.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    // 你可以在这里定义额外的查询方法，例如通过用户名查找用户
    AdminUser findByUsername(String username);
}
