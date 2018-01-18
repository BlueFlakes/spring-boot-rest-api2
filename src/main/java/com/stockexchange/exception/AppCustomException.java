package com.stockexchange.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class AppCustomException extends Exception {
    public ErrorCode getErrorCode() {
        ResponseStatus responseStatus = this.getClass().getDeclaredAnnotation(ResponseStatus.class);

        if (responseStatus != null) {
            return new ErrorCode(responseStatus.value(), responseStatus.reason());
        }

        return null;
    }
}
