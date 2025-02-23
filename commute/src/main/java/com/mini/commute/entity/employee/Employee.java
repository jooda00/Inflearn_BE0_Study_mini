package com.mini.commute.entity.employee;

import com.mini.commute.entity.annual.Annual;
import com.mini.commute.entity.annual.AnnualUsage;
import com.mini.commute.entity.team.Team;
import com.mini.commute.entity.work.Work;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    private String name;
    private LocalDate birthday;
    private LocalDate workStartDate;
    private Role role;
    private boolean isNewbie;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩을 해야 join을 사용하지 않고 employee를 가져온다.(Team 객체는 프록시 객체로 로딩)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "employee")
    private List<Work> works = new ArrayList<Work>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANNUAL_ID")
    private Annual annual;

    @OneToMany(mappedBy = "employee")
    private List<AnnualUsage> annualUsages = new ArrayList<AnnualUsage>();

    public Employee(String name, LocalDate birthday, LocalDate workStartDate, Role role) {
        this.name = name;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
        this.role = role;
        calculateIsNewbie();
    }

    public void calculateIsNewbie() {
        LocalDate targetDate = LocalDate.of(2024, 1, 1);
        this.isNewbie = !workStartDate.isBefore(targetDate);
    }

    /* 연관관계 메소드 */
    public void setTeam(Team team) {
        this.team = team;
        team.getEmployees().add(this);
    }

    public void setAnnual(Annual annual) {
        this.annual = annual;
        if (annual.getEmployee() != this) {
            annual.setEmployee(this);
        }
    }
}
