package com.example.currancyexchange.domain;

import java.math.BigDecimal;
import java.util.Map;

public class RatesCryptoExchangeResponse {

    private Map<String, BigDecimal> rates;

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public void add(String currencyName, BigDecimal rate){
        rates.put(currencyName, rate);
    }
}
