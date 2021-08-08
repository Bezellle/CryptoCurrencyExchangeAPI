package com.example.currancyexchange;

import com.example.currancyexchange.domain.RequestedRates;
import com.example.currancyexchange.exceptions.WrongCurrencyException;

import java.util.List;

public class CryptoExchangeValidator {

    public static void validateCurrency(RequestedRates rates, String from, List<String> to) {
        if (!rates.getRates().containsKey(from)){
            throw new WrongCurrencyException("Given Currency not found!");
        };
        if (!to.isEmpty() && to != null) {
            for (String currency : to) {
                if(!rates.getRates().containsKey(currency)){
                    throw new WrongCurrencyException("Given Currency not found!");
                };
            }
        }
    }
}
