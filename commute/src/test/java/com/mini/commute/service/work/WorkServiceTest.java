package com.mini.commute.service.work;

import com.mini.commute.dto.work.WorkResponseWithSum;
import com.mini.commute.entity.employee.Employee;
import com.mini.commute.entity.work.Work;
import com.mini.commute.repository.employee.EmployeeRepository;
import com.mini.commute.repository.work.WorkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkServiceTest {
    private final WorkRepository workRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkService workService;
    private Employee employee;
    private List<Work> work = new ArrayList<Work>();

    @Autowired
    public WorkServiceTest(WorkRepository workRepository, EmployeeRepository employeeRepository, WorkService workService) {
        this.workRepository = workRepository;
        this.employeeRepository = employeeRepository;
        this.workService = workService;
    }

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employeeRepository.save(employee);
        work.add(new Work(LocalDate.of(2024, 02, 01)));
        work.add(new Work(LocalDate.of(2024, 02, 11)));
        work.add(new Work(LocalDate.of(2024, 02, 10)));
        // 2월에 속하지 않음
        work.add(new Work(LocalDate.of(2024, 03, 10)));
        for(Work w : work) {
            w.setEmployee(employee);
            w.startWork();
            w.endWork();
            workRepository.save(w);
        }
    }

/*    @DisplayName("회사 직원이 아니면 출근하지 못한다.")
    @Test
    void notExistedEmployee() {
        assertThatThrownBy(() -> workService.startWork(2L, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 직원은 회사에 존재하지 않습니다.");
    }*/

    @DisplayName("해당하는 달에 일한 근무 시간과 날짜를 반환한다.")
    @Test
    void getWorkingMinutesByMonth() {
        LocalDate date = LocalDate.of(2024, 02, 01);
        WorkResponseWithSum response = workService.getWorkingMinutes(employee.getId(), date);

        assertThat(work.size() - 1).isEqualTo(response.getDatail().size());
        assertThat(0).isEqualTo(response.getSum());
    }
}
