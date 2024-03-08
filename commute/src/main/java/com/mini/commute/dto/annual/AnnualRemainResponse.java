package com.mini.commute.dto.annual;

import lombok.Getter;

@Getter
public class AnnualRemainResponse {
    private int remainAnnual;

    public AnnualRemainResponse(int remainAnnual) {
        this.remainAnnual = remainAnnual;
    }
}
