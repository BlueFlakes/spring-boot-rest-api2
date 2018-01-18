package com.stockexchange.model;

import org.springframework.http.HttpStatus;

public class ErrorCode {
    private HttpStatus httpStatus;
    private String reason;

    public ErrorCode(HttpStatus httpStatus, String reason) {
        this.httpStatus = httpStatus;
        this.reason = reason;
    }

    public HttpStatus getHttpStatus( ) {
        return httpStatus;
    }

    public String getReason( ) {
        return reason;
    }
}
