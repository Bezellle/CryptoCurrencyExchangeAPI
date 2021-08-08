package com.example.currancyexchange.domain.currency;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
