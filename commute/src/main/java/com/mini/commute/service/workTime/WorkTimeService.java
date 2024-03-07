package com.mini.commute.service.workTime;

import com.mini.commute.dto.workTime.WorkTimeStartResponse;
import com.mini.commute.entity.employee.Employee;
import com.mini.commute.entity.workTime.WorkTime;
import com.mini.commute.repository.employee.EmployeeRepository;
import com.mini.commute.repository.workTime.WorkTimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class WorkTimeService {
    private final WorkTimeRepository workTimeRepository;
    private final EmployeeRepository employeeRepository;

    public WorkTimeService(WorkTimeRepository workTimeRepository, EmployeeRepository employeeRepository) {
        this.workTimeRepository = workTimeRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public WorkTimeStartResponse startWork(Long id, LocalDate date) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 직원은 회사에 존재하지 않습니다."));
        if(workTimeRepository.findByDateAndEmployee(date, employee) != null) {
            throw new IllegalArgumentException("해당 직원은 이미 출근한 상태입니다.");
        }
        WorkTime workTime = new WorkTime(date);
        workTime.setEmployee(employee);
        workTime.startWork();
        workTimeRepository.save(workTime);

        return new WorkTimeStartResponse(workTime);
    }
}
