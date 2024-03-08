package com.mini.commute.entity.annual;

import com.mini.commute.entity.employee.Employee;
import com.mini.commute.entity.employee.Role;
import com.mini.commute.repository.employee.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AnnualTest {
    private Employee employee;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AnnualTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @DisplayName("신입은 11개의 연차를 가진다.")
    @Test
    void getElevenAnnualIsNewbie() {
        employee = new Employee("신입", LocalDate.of(1990,01,01), LocalDate.of(2024,01,02), Role.MEMBER);
        employeeRepository.save(employee);

        Annual annual = new Annual(employee);

        employee.setAnnual(annual);

        assertThat(11).isEqualTo(annual.getRemainAnnual());
    }

    @DisplayName("신입이 아닌 직원은 15개의 연차를 가진다.")
    @Test
    void getFifteenAnnualIsNewbie() {
        employee = new Employee("대리", LocalDate.of(1990,01,01), LocalDate.of(2023,01,02), Role.MEMBER);
        employeeRepository.save(employee);

        Annual annual = new Annual(employee);

        employee.setAnnual(annual);

        assertThat(15).isEqualTo(annual.getRemainAnnual());
    }

    @DisplayName("연차를 신청하면 연차 1개가 차감된다.")
    @Test
    void useAnnual() {
        employee = new Employee("신입", LocalDate.of(1990,01,01), LocalDate.of(2024,01,02), Role.MEMBER);
        employeeRepository.save(employee);

        Annual annual = new Annual(employee);

        employee.setAnnual(annual);
        annual.useAnnual();

        assertThat(10).isEqualTo(annual.getRemainAnnual());
    }
}
