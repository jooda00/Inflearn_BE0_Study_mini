package com.mini.commute.entity.annual;

import com.mini.commute.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class AnnualUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNUAL_USAGE_ID")
    private Long id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    public AnnualUsage(LocalDate date) {
        this.date = date;
    }

    /* 연관관계 메소드 */
    public void setEmployee(Employee employee) {
        this.employee = employee;
        employee.getAnnualUsages().add(this);
    }
}
