package com.stockexchange.exception;

public class AlreadyOccupiedIdException extends AppCustomException {
    public AlreadyOccupiedIdException(String message) {
        super(message);
    }
}
