package com.example.currancyexchange.exceptions;

public class WrongCurrencyException extends NullPointerException{

    public WrongCurrencyException(String errorMessage){
        super(errorMessage);
    }
}
