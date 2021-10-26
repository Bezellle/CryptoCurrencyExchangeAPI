package com.example.currancyexchange.domain.exchange;

import java.math.BigDecimal;

public class ExchangeResult {
    private String currency;
    private BigDecimal rate;
    private BigDecimal fee;
    private BigDecimal result;


    public ExchangeResult(String currency, BigDecimal rate, BigDecimal fee, BigDecimal result) {
        this.currency = currency;
        this.rate = rate;
        this.fee = fee;
        this.result = result;
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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
