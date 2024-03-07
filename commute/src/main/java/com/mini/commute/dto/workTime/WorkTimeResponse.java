package com.mini.commute.dto.workTime;

import com.mini.commute.entity.workTime.WorkTime;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkTimeResponse {
    private boolean isArrived;
    private LocalDateTime startWorkTime;
    private LocalDateTime endWorkTime;

    public WorkTimeResponse(WorkTime workTime) {
        this.isArrived = workTime.isArrived();
        this.startWorkTime = workTime.getStartWorkTime();
        this.endWorkTime = workTime.getEndWorkTime();
    }
}
