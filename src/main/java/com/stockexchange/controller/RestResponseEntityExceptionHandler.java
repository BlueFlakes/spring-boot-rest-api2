package com.stockexchange.controller;


import com.stockexchange.exception.AppCustomException;
import com.stockexchange.model.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AppCustomException.class})
    protected <T extends AppCustomException> ResponseEntity<ErrorCode> handleBadRequest(T ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()
                                              .value()).body(errorCode);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleError(HttpServletRequest request, Exception e)   {
//        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
//        return new ModelAndView("error");
//    }
}
