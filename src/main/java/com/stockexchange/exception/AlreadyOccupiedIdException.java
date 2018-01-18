package com.stockexchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Given id is already occupied.")
public class AlreadyOccupiedIdException extends AppCustomException {
}
