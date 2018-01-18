package com.stockexchange.service.loggerService;

import com.stockexchange.Application;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.stereotype.Service;

@Service
public class Log4j2Adapter implements LoggerAble {
    private final Logger logger = LogManager.getLogger(Application.class);
    private final Marker marker = MarkerManager.getMarker("START");

    @Override
    public void logInfo(String message) {
        logger.info(marker, message);
    }

    @Override
    public void logError(String message) {
        logger.error(marker, message);
    }
}
