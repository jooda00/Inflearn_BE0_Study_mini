package com.mini.commute.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponseData> handleException(CustomException e) {
        return ErrorResponseData.toResponseEntity(e.getErrorCode());
    }

}
