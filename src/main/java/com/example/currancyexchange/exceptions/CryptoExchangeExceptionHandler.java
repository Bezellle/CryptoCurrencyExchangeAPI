package com.example.currancyexchange.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CryptoExchangeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ArithmeticException.class)
    protected ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request){
        String responseBody = "Cannot calculate exchange result for currancy with rate=0 ";
        System.out.println(responseBody);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = WrongCurrencyException.class)
    protected ResponseEntity<Object> handlerWrongCurrencyException(WrongCurrencyException ex, WebRequest request){
        System.out.println(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = WrongAmountException.class)
    protected ResponseEntity<Object> handlerWrongAmountException(WrongAmountException ex, WebRequest request){
        System.out.println(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = HttpStatusCodeException.class)
    protected ResponseEntity<Object> handlerHttpStatusCodeException(HttpStatusCodeException ex, WebRequest request){
        String message = "Connection Problem \\n";
        System.out.println(message);
        HttpHeaders headers= ex.getResponseHeaders();
        System.out.println(headers.get("Content-Type"));
        System.out.println(headers.get("Server"));
        return handleExceptionInternal(ex, message, new HttpHeaders(), BAD_REQUEST, request);
    }

}
