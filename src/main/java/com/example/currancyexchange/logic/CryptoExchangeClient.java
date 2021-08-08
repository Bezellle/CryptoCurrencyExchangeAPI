package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CurrancyNomicResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public CurrancyNomicResponse[] getAPICurrencyRequest() {

        return restTemplate.getForObject(nomicsAPI_URL+key, CurrancyNomicResponse[].class);
    }
}
