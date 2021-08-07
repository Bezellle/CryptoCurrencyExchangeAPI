package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.CurrancyRequest;
import com.example.currancyexchange.domain.NomicCombinedResponse;
import com.example.currancyexchange.domain.RatesCryptoExchangeResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CryptoExchangeResponseMapper {

    public CryptoExchangeResponse getResponse(CurrancyRequest[] currancyRequests, List<String> filters){
        // pass
        return new CryptoExchangeResponse();
    }

    public RatesCryptoExchangeResponse createMapWithRates( CurrancyRequest[] currancyRequests){
        RatesCryptoExchangeResponse ratesMap = new RatesCryptoExchangeResponse();
        ratesMap.setRates(Arrays.stream(currancyRequests).collect(Collectors.toMap(CurrancyRequest::getCurrency, CurrancyRequest::getRate)));
        return ratesMap;
    }
}
