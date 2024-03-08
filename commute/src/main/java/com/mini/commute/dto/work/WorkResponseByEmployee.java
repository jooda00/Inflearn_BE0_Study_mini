package com.mini.commute.dto.work;

import com.mini.commute.entity.work.Work;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WorkResponseByEmployee {
    private LocalDate date;
    private long workingMinutes;

    public WorkResponseByEmployee(Work work) {
        this.date = work.getDate();
        this.workingMinutes = work.getWorkingMinutes();
    }
}
