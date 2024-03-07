package com.mini.commute.repository.workTime;

import com.mini.commute.entity.employee.Employee;
import com.mini.commute.entity.workTime.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {
    WorkTime findByDateAndEmployee(LocalDate date, Employee employee);
}
