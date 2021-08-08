package com.example.currancyexchange;

import com.example.currancyexchange.domain.*;
import org.springframework.stereotype.Component;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
