package com.mini.commute.entity.work;

import com.mini.commute.entity.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkTest {
    private Work work;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        work = new Work();
        work.setEmployee(employee);
    }

    @DisplayName("아직 출근하지 않은 회사 직원은 출근할 수 있다.")
    @Test
    void startWork() {
        // 출근 전
        assertThat(false).isEqualTo(work.isArrived());

        work.startWork();

        // 출근 후
        assertThat(true).isEqualTo(work.isArrived());
    }

    @DisplayName("출근한 회사 직원은 퇴근할 수 있다.")
    @Test
    void endWork() {
        work.startWork();
        work.endWork();

        assertThat(false).isEqualTo(work.isArrived());
    }

    @DisplayName("퇴근한 순간 근무 시간(분)이 계산된다.")
    @Test
    void calculateWorkingMinutes() {
        work.startWork();
        LocalDateTime start = work.getStartWorkTime();

        work.endWork();
        LocalDateTime end = work.getEndWorkTime();

        long workingMinutes = ChronoUnit.MINUTES.between(start, end);

        assertThat(workingMinutes).isEqualTo(work.getWorkingMinutes());
    }
}
