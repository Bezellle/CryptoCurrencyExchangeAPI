package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CurrencyNomicResponse;
import com.example.currancyexchange.domain.currency.RequestedRates;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CryptoExchangeResponseMapper {

    public static RequestedRates createMapWithRatesFromApiResponse(CurrencyNomicResponse[] currencyNomicRespons){
        RequestedRates ratesMap = new RequestedRates();
        ratesMap.setRates(Arrays.stream(currencyNomicRespons).collect(Collectors.toMap(CurrencyNomicResponse::getCurrency, CurrencyNomicResponse::getRate)));
        return ratesMap;
    }
}
