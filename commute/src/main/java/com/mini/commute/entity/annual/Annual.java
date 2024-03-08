package com.mini.commute.entity.annual;

import com.mini.commute.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Annual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNUAL_ID")
    private Long id;

    private int remainAnnual;

    @OneToOne(mappedBy = "annual")
    private Employee employee;

    public Annual(Employee employee) {
        this.employee = employee;
        initializeRemainAnnual();
    }

    public void initializeRemainAnnual() {
        if(employee.isNewbie()) {
            this.remainAnnual = 11;
        }
        else {
            this.remainAnnual = 15;
        }
    }

    /* 연관관계 메소드 */
    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee.getAnnual() != this) {
            employee.setAnnual(this);
        }
    }
}
