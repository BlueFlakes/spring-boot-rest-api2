package com.stockexchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Record with given id is already archived.")
public class ArchivedDataException extends AppCustomException {
}
