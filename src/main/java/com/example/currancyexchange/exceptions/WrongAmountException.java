package com.example.currancyexchange.exceptions;

public class WrongAmountException extends ArithmeticException {
    public WrongAmountException(String errorMessage){
        super(errorMessage);
    }
}
