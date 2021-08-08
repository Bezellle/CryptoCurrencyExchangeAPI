package com.example.currancyexchange.domain;

import java.math.BigDecimal;

public class ExchangeResult {
    private String currency;
    private BigDecimal rate;
    private BigDecimal result;
    private final BigDecimal fee= BigDecimal.valueOf(1).movePointLeft(4);

    public final BigDecimal getFee() {
        return fee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
