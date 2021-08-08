package com.example.currancyexchange.currencyExchange;

import com.example.currancyexchange.domain.currency.CurrancyRequest;
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

    public RequestedRates createMapWithRates(CurrancyRequest[] currancyRequests){
        RequestedRates ratesMap = new RequestedRates();
        ratesMap.setRates(Arrays.stream(currancyRequests).collect(Collectors.toMap(CurrancyRequest::getCurrency, CurrancyRequest::getRate)));
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
