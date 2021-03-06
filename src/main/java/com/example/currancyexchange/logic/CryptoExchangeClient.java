package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CurrencyNomicResponse;
import com.example.currancyexchange.domain.currency.RequestedRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoExchangeClient {

    public String getNomicsAPI_URL() {
        return nomicsAPI_URL;
    }

    public void setNomicsAPI_URL(String nomicsAPI_URL) {
        this.nomicsAPI_URL = nomicsAPI_URL;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Value("${nomicsAPI_URL}")
    private String nomicsAPI_URL;
    @Value("${key}")
    private String key;
    private final RestTemplate restTemplate;

    public CryptoExchangeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RequestedRates getAPICurrencyRequest() {
        return CryptoExchangeResponseMapper.createMapWithRatesFromApiResponse(restTemplate.getForObject(nomicsAPI_URL+key, CurrencyNomicResponse[].class));
    }
}
