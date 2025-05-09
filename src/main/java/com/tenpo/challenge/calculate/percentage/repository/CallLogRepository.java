package com.tenpo.challenge.calculate.percentage.repository;

import com.tenpo.challenge.calculate.percentage.entity.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CallLogRepository extends JpaRepository<CallLog,Long> {
}
