package com.mini.commute.service.work;

import com.mini.commute.repository.employee.EmployeeRepository;
import com.mini.commute.repository.work.WorkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkServiceTest {
    private final WorkRepository workRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkService workService;

    @Autowired
    public WorkServiceTest(WorkRepository workRepository, EmployeeRepository employeeRepository, WorkService workService) {
        this.workRepository = workRepository;
        this.employeeRepository = employeeRepository;
        this.workService = workService;
    }

    @DisplayName("회사 직원이 아니면 출근하지 못한다.")
    @Test
    void notExistedEmployee() {
        assertThatThrownBy(() -> workService.startWork(1L, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 직원은 회사에 존재하지 않습니다.");
    }
}
