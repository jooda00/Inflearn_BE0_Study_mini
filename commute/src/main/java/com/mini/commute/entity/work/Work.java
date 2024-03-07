package com.mini.commute.entity.work;

import com.mini.commute.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@NoArgsConstructor
@Getter
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long workingMinutes;
    private LocalDate date;
    private long overtimeMinutes;

    private boolean isArrived;
    private LocalDateTime startWorkTime;
    private LocalDateTime endWorkTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    public Work(LocalDate date) {
        this.date = date;
    }

    public void startWork() {
        if(!this.isArrived) {
            this.isArrived = true;
            this.startWorkTime = LocalDateTime.now();
        }
    }

    public void endWork() {
        if(this.isArrived) {
            this.isArrived = false;
            this.endWorkTime = LocalDateTime.now();
            this.workingMinutes = ChronoUnit.MINUTES.between(this.startWorkTime, this.endWorkTime);
        }
    }

    /* 연관관계 메소드 */
    public void setEmployee(Employee employee) {
        this.employee = employee;
        employee.getWorks().add(this);
    }
}
