package com.mini.commute.common.exception;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponseData {
    private int code;
    private String name;
    private String message;

    public ErrorResponseData(int code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public ErrorResponseData(ErrorCode errorCode) {
        this.code = errorCode.getHttpStatus().value();
        this.name = errorCode.getCustomCode();
        this.message = errorCode.getMessage();
    }

    public static ResponseEntity<ErrorResponseData> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponseData(errorCode));
    }
}
