package com.mini.commute.repository.annual;

import com.mini.commute.entity.annual.Annual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnualRepository extends JpaRepository<Annual, Long> {
    Annual findByEmployeeId(Long id);
}
