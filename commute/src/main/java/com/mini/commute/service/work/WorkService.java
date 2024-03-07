package com.mini.commute.service.work;

import com.mini.commute.dto.work.WorkResponse;
import com.mini.commute.dto.work.WorkResponseByEmployee;
import com.mini.commute.dto.work.WorkResponseWithSum;
import com.mini.commute.entity.employee.Employee;
import com.mini.commute.entity.work.Work;
import com.mini.commute.repository.employee.EmployeeRepository;
import com.mini.commute.repository.work.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class WorkService {
    private final WorkRepository workRepository;
    private final EmployeeRepository employeeRepository;

    public WorkService(WorkRepository workRepository, EmployeeRepository employeeRepository) {
        this.workRepository = workRepository;
        this.employeeRepository = employeeRepository;
    }

    // 출근 -> 퇴근 -> 출근도 "해당 직원은 이미 출근한 상태입니다." 예외가 던져짐
    @Transactional
    public WorkResponse startWork(Long id, LocalDate date) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 직원은 회사에 존재하지 않습니다."));
        if(workRepository.findByDateAndEmployee(date, employee) != null) {
            throw new IllegalArgumentException("해당 직원은 이미 출근한 상태입니다.");
        }
        Work work = new Work(date);
        work.setEmployee(employee);
        work.startWork();
        workRepository.save(work);

        return new WorkResponse(work);
    }

    // 퇴근 후 또 퇴근은?
    @Transactional
    public WorkResponse endWork(Long id, LocalDate date) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 직원은 회사에 존재하지 않습니다."));
        Work work = workRepository.findByDateAndEmployee(date, employee);
        if(work == null) {
            throw new IllegalArgumentException("해당 직원은 금일 출근하지 않았습니다.");
        }
        work.endWork();

        return new WorkResponse(work);
    }

    public WorkResponseWithSum getWorkingMinutes(Long id, LocalDate date) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 직원은 회사에 존재하지 않습니다."));
        LocalDate startDate = date.withDayOfMonth(1);
        LocalDate endDate = date.withDayOfMonth(date.lengthOfMonth());

        // querydsl?
        List<Work> workTimes = workRepository.findAllByEmployeeIdAndDateBetweenAndIsArrivedFalseOrderByDateAsc(id, startDate, endDate);
        if(workTimes.isEmpty()) {
            throw new IllegalArgumentException("해당 직원은 이 기간 동안 출근하지 않았습니다.");
        }

        long sum = workTimes.stream().mapToLong(Work::getWorkingMinutes).sum();
        List<WorkResponseByEmployee> responses = workTimes.stream()
                .map(workTime -> new WorkResponseByEmployee(workTime))
                .collect(Collectors.toList());

        return new WorkResponseWithSum(responses, sum);
    }
}
