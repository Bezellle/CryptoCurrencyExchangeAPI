package com.example.currancyexchange.domain.exchange;

import java.math.BigDecimal;

public class ExchangeResult {
    private String currency;
    private BigDecimal rate;
    private BigDecimal result;
    private final BigDecimal fee = new BigDecimal("0.0001");

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
