package com.example.currancyexchange;

import com.example.currancyexchange.domain.CurrancyRequest;
import com.example.currancyexchange.domain.NomicCombinedResponse;
import com.example.currancyexchange.domain.RatesCryptoExchangeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
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

    public CurrancyRequest[] getNomicCombinesResponseList() {

        CurrancyRequest[] response = restTemplate.getForObject(nomicsAPI_URL+key, CurrancyRequest[].class);
        return response;
    }
}
