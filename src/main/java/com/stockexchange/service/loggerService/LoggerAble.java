package com.stockexchange.service.loggerService;

import org.springframework.stereotype.Component;


public interface LoggerAble {
    void logInfo(String message);
    void logError(String message);
}
