package com.mini.commute.dto.workTime;

import com.mini.commute.entity.workTime.WorkTime;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkTimeStartResponse {
    private boolean isArrived;
    private LocalDateTime startWorkTime;

    public WorkTimeStartResponse(WorkTime workTime) {
        this.isArrived = workTime.isArrived();
        this.startWorkTime = workTime.getStartWorkTime();
    }
}
