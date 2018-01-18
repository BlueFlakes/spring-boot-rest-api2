package com.stockexchange.exception;

import com.stockexchange.model.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

public class AppCustomException extends Exception {
    public ErrorCode getErrorCode() {
        ResponseStatus responseStatus = this.getClass().getDeclaredAnnotation(ResponseStatus.class);

        if (responseStatus != null) {
            return new ErrorCode(responseStatus.value(), responseStatus.reason());
        }

        return null;
    }
}
