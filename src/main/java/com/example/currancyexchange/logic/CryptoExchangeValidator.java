package com.example.currancyexchange.currencyExchange;

import com.example.currancyexchange.domain.currency.RequestedRates;
import com.example.currancyexchange.exceptions.WrongCurrencyException;

import java.util.List;

public class CryptoExchangeValidator {

    public static void validateCurrency(RequestedRates rates, String from, List<String> currencyToList) {
        if (!rates.getRates().containsKey(from)){
            throw new WrongCurrencyException(String.format("Currency not found: %s", from));
        }
        if (!currencyToList.isEmpty() && currencyToList != null) {
            for (String currency : currencyToList) {
                if(!rates.getRates().containsKey(currency)){
                    throw new WrongCurrencyException(String.format("Currency not found: %s", currency));
                }
            }
        }
    }
}
