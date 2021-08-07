package com.example.currancyexchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CryptoExchangeClient {

    @Value("${nomicsAPI_URL}")
    private String nomicsAPI_URL;
    @Value("${key}")
    private String key;
    private final RestTemplate restTemplate;

    public CryptoExchangeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NomicCombinedResponse getCurrenciesRates(String currency, List<String> filters) {
        NomicCombinedResponse nomicCombinedResponse = new NomicCombinedResponse();
        if(filters != null && !filters.isEmpty()){
            for(String filter: filters) {
                String filterURL = "&convert=" + filter;
                CurrancyRequest[] response = restTemplate.getForObject(nomicsAPI_URL + key + "&ids=" + currency + filterURL, CurrancyRequest[].class);
                nomicCombinedResponse.add(response[0]);
            }
        }
        else{
            CurrancyRequest[] response = restTemplate.getForObject(nomicsAPI_URL+key+"&ids="+ currency, CurrancyRequest[].class);
            nomicCombinedResponse.add(response[0]);
        }

        return nomicCombinedResponse;
    }
}
