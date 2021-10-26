package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.RequestedRates;
import com.example.currancyexchange.exceptions.WrongAmountException;
import com.example.currancyexchange.exceptions.WrongCurrencyException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CryptoExchangeValidator {

    public static void validateCurrency(RequestedRates rates, String from, List<String> currencyToList) {
        Optional.of(from).filter(f -> rates.getRates().containsKey(from))
                .orElseThrow(()->{throw new WrongCurrencyException(String.format("Currency not found: %s", from));});

//        Optional.of(currencyToList).
        if (!rates.getRates().containsKey(from)){
            throw new WrongCurrencyException(String.format("Currency not found: %s", from));
        }
        if (!currencyToList.isEmpty()) {
            for (String currency : currencyToList) {
                if(currency.equals("")){
                    continue;
                }
                if(!rates.getRates().containsKey(currency)){
                    throw new WrongCurrencyException(String.format("Currency not found: %s", currency));
                }
            }
        }
    }

    public static void validateAmount(BigDecimal amount){
        if(amount.doubleValue() <= 0){
            throw  new WrongAmountException(String.format("Amount error - Amount has to be greater than 0. Given: %s", amount));
        }
    }
}
