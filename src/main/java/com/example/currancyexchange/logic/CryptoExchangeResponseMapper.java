package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CurrencyNomicResponse;
import com.example.currancyexchange.domain.currency.RequestedRates;
import com.example.currancyexchange.domain.exchange.ExchangeRequestBody;
import com.example.currancyexchange.domain.exchange.ExchangeResponse;
import com.example.currancyexchange.domain.exchange.ExchangeResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CryptoExchangeResponseMapper {

    public RequestedRates createMapWithRatesFromApiResponse(CurrencyNomicResponse[] currencyNomicRespons){
        RequestedRates ratesMap = new RequestedRates();
        ratesMap.setRates(Arrays.stream(currencyNomicRespons).collect(Collectors.toMap(CurrencyNomicResponse::getCurrency, CurrencyNomicResponse::getRate)));
        return ratesMap;
    }

    public ExchangeResponse createExchangeResponse(ExchangeRequestBody exchangeRequestBody, ArrayList<ExchangeResult> results){
        ExchangeResponse exchangeResponse = new ExchangeResponse();
        exchangeResponse.setFrom(exchangeRequestBody.getFrom());
        exchangeResponse.setAmount(exchangeRequestBody.getAmount());
        exchangeResponse.setExchangeResultList(results);
        return exchangeResponse;
    }
}
