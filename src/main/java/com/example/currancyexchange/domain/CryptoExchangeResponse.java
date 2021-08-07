package com.example.currancyexchange.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CryptoExchangeResponse {
    private String source;
    // private RatesCryptoExchangeResponse rates;
    private Map<String, BigDecimal> rates = new HashMap<>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    @Override
    public String toString(){

        return "";
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
}
