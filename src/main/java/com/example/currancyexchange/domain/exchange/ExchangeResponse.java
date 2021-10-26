package com.example.currancyexchange.domain.exchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExchangeResponse {
    private String from;
    private BigDecimal amount;
    private List<ExchangeResult> exchangeResultList;

    public ExchangeResponse(String from, BigDecimal amount, List<ExchangeResult> exchangeResultList) {
        this.from = from;
        this.amount = amount;
        this.exchangeResultList = exchangeResultList;
    }

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

    public List<ExchangeResult> getExchangeResultList() {
        return exchangeResultList;
    }

    public void setExchangeResultList(ArrayList<ExchangeResult> exchangeResultList) {
        this.exchangeResultList = exchangeResultList;
    }
}
