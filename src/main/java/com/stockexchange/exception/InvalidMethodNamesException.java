package com.stockexchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "One of models is corrupted.")
public class InvalidMethodNamesException extends AppCustomException {
}
