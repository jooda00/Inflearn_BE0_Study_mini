package com.mini.commute.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    EMPLOYEE_NOT_FOUND(HttpStatus.NOT_FOUND, "E-01", "해당 직원은 회사에 존재하지 않습니다."),
    EMPLOYEE_NOT_ARRIVED_AT_COMPANY(HttpStatus.NOT_FOUND, "E-02", "해당 직원은 금일 출근하지 않았습니다."),
    EMPLOYEE_NOT_WORK_DURING_DATE(HttpStatus.NOT_FOUND, "E-03", "해당 직원은 이 기간 동안 출근하지 않았습니다."),
    EMPLOYEE_ALREADY_ARRIVED_AT_COMPANY(HttpStatus.BAD_REQUEST, "E-04", "이미 출근처리 되었습니다."),
    EMPLOYEE_ALREADY_LEAVE_COMPANY(HttpStatus.BAD_REQUEST, "E-04", "이미 퇴근처리 되었습니다."),

    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "T-01", "팀이 존재하지 않습니다"),

    REMAIN_ANNUAL_NOT_EXISTED(HttpStatus.BAD_REQUEST, "A-01", "모든 연차를 소진했습니다."),
    ANNUAL_ALREADY_USED(HttpStatus.BAD_REQUEST, "A-02", "해당 날짜에 이미 연차를 신청했습니다."),
    ANNUAL_REGISTRATION_DATE_HAS_PASSED(HttpStatus.BAD_REQUEST, "A-03", "연차 신청일이 지났습니다.");

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String customCode, String message) {
        this.httpStatus = httpStatus;
        this.customCode = customCode;
        this.message = message;
    }
}
