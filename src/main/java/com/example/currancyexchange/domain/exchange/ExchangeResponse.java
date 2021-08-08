package com.example.currancyexchange.domain.exchange;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ExchangeResponse {
    private String from;
    private BigDecimal amount;
    private ArrayList<ExchangeResult> exchangeResultList;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ArrayList<ExchangeResult> getExchangeResultList() {
        return exchangeResultList;
    }

    public void setExchangeResultList(ArrayList<ExchangeResult> exchangeResultList) {
        this.exchangeResultList = exchangeResultList;
    }
}
