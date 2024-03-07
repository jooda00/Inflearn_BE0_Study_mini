package com.mini.commute.repository.work;

import com.mini.commute.entity.employee.Employee;
import com.mini.commute.entity.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    Work findByDateAndEmployee(LocalDate date, Employee employee);

    List<Work> findAllByEmployeeIdAndDateBetweenAndIsArrivedFalseOrderByDateAsc(Long id, LocalDate startDate, LocalDate endDate);
}
