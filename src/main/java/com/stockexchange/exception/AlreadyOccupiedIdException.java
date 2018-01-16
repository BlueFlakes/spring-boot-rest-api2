package com.stockexchange.exception;

public class AlreadyOccupiedIdException extends Exception {
    public AlreadyOccupiedIdException(String message) {
        super(message);
    }
}
