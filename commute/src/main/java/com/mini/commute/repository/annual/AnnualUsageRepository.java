package com.mini.commute.repository.annual;

import com.mini.commute.entity.annual.AnnualUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AnnualUsageRepository extends JpaRepository<AnnualUsage, Long> {
    AnnualUsage findByEmployeeIdAndDate(Long id, LocalDate date);
}
