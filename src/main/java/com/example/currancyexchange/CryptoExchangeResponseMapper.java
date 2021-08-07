package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.CurrancyRequest;
import com.example.currancyexchange.domain.RequestedRates;
import org.springframework.stereotype.Component;

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
}
