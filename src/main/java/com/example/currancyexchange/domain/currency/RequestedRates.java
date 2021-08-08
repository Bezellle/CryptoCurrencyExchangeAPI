package com.example.currancyexchange.domain.currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RequestedRates {

    private Map<String, BigDecimal> rates = new HashMap<>();

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
