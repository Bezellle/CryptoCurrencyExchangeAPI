package com.example.currancyexchange.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CryptoExchangeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ArithmeticException.class)
    protected ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request){
        String responseBody = "Cannot calculate exchange result for currancy with rate=0 ";
        System.out.println("Dividing by 0");
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
