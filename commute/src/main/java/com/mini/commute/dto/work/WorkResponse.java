package com.mini.commute.dto.work;

import com.mini.commute.entity.work.Work;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkResponse {
    private boolean isArrived;
    private LocalDateTime startWorkTime;
    private LocalDateTime endWorkTime;

    public WorkResponse(Work work) {
        this.isArrived = work.isArrived();
        this.startWorkTime = work.getStartWorkTime();
        this.endWorkTime = work.getEndWorkTime();
    }
}
