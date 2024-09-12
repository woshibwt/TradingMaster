package com.bwt.tradingmaster.repository;

import com.bwt.tradingmaster.model.AdminActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminActivityLogRepository extends JpaRepository<AdminActivityLog, Long> {
}
