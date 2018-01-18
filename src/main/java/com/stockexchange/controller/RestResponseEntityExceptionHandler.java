package com.stockexchange.controller;


import com.stockexchange.exception.AppCustomException;
import com.stockexchange.exception.InvalidDataFormatException;
import com.stockexchange.model.ErrorCode;
import com.stockexchange.service.loggerService.LoggerAble;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private LoggerAble logger;

    public RestResponseEntityExceptionHandler(LoggerAble logger) {
        this.logger = logger;
    }

    @ExceptionHandler(value = {AppCustomException.class})
    protected <T extends AppCustomException> ResponseEntity<ErrorCode> handleKnownExceptions(T ex) {
        ErrorCode errorCode = ex.getErrorCode();
        this.logger.logError(errorCode.toString());

        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleError(HttpServletRequest request, Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Very bad");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorCode> handleValidationError() {
        return handleKnownExceptions(new InvalidDataFormatException());
    }
}
