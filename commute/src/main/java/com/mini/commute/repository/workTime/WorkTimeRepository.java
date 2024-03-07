package com.mini.commute.repository.workTime;

import com.mini.commute.entity.workTime.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {
}
