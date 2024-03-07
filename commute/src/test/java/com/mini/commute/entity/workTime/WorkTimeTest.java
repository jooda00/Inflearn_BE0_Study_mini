package com.mini.commute.entity.workTime;

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
public class WorkTimeTest {
    private WorkTime workTime;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        workTime = new WorkTime();
        workTime.setEmployee(employee);
    }

    @DisplayName("아직 출근하지 않은 회사 직원은 출근할 수 있다.")
    @Test
    void startWork() {
        // 출근 전
        assertThat(false).isEqualTo(workTime.isArrived());

        workTime.startWork();

        // 출근 후
        assertThat(true).isEqualTo(workTime.isArrived());
    }

    @DisplayName("출근한 회사 직원은 퇴근할 수 있다.")
    @Test
    void endWork() {
        workTime.startWork();
        workTime.endWork();

        assertThat(false).isEqualTo(workTime.isArrived());
    }

    @DisplayName("퇴근한 순간 근무 시간(분)이 계산된다.")
    @Test
    void calculateWorkingMinutes() {
        workTime.startWork();
        LocalDateTime start = workTime.getStartWorkTime();

        workTime.endWork();
        LocalDateTime end = workTime.getEndWorkTime();

        long workingMinutes = ChronoUnit.MINUTES.between(start, end);

        assertThat(workingMinutes).isEqualTo(workTime.getWorkingMinutes());
    }
}
