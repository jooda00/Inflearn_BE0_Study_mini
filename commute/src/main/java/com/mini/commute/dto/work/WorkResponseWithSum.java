package com.mini.commute.dto.work;

import lombok.Getter;

import java.util.List;

@Getter
public class WorkResponseWithSum {
    private List<WorkResponseByEmployee> datail;
    private long sum;

    public WorkResponseWithSum(List<WorkResponseByEmployee> datail, long sum) {
        this.datail = datail;
        this.sum = sum;
    }
}
