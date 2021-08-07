package com.example.currancyexchange.domain;

public class CryptoExchangeResponse {
    private String source;
    private RatesCryptoExchangeResponse rates;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public RatesCryptoExchangeResponse getRates() {
        return rates;
    }

    public void setRates(RatesCryptoExchangeResponse rates) {
        this.rates = rates;
    }

    @Override
    public String toString(){

        return "";
    }
}
