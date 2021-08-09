package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CryptoExchangeResponse;
import com.example.currancyexchange.domain.exchange.ExchangeRequestBody;
import com.example.currancyexchange.domain.exchange.ExchangeResult;
import com.example.currancyexchange.domain.currency.RequestedRates;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class CryptoExchangeCalculator {
    private final BigDecimal commission = new BigDecimal("0.01");

    public CryptoExchangeResponse calculateResponseRates(RequestedRates rates, String currencyFrom, List<String> filters) {
        CryptoExchangeResponse result = new CryptoExchangeResponse();
        result.setSource(currencyFrom);
        if (filters != null && !filters.isEmpty()) {
            for (String filter : filters) {
                if (rates.getRates().get(filter) != null && rates.getRates().get(filter).signum() > 0) {
                    BigDecimal rate = calculateRate(rates, currencyFrom, filter);
                    result.getRates().put(filter, rate);
                }
            }
        } else {
            for (String currencyTo : rates.getRates().keySet()) {
                if (rates.getRates().get(currencyTo) != null && rates.getRates().get(currencyTo).signum() > 0) {
                    BigDecimal rate = calculateRate(rates, currencyFrom, currencyTo);
                    result.getRates().put(currencyTo, rate);
                }
            }
        }
        return result;
    }

    private BigDecimal calculateRate(RequestedRates rates, String currencyFrom, String currencyTo) {
        return rates.getRates().get(currencyFrom).divide(rates.getRates().get(currencyTo), 8, RoundingMode.HALF_UP);
    }

    public ArrayList<ExchangeResult> calculateExchangeResults(RequestedRates requestedRates, ExchangeRequestBody exchangeRequestBody) {
        ArrayList<ExchangeResult> exchangeResults = new ArrayList<>();
        for (String currencyTo : exchangeRequestBody.getTo()) {
            ExchangeResult result = new ExchangeResult();
            result.setCurrency(currencyTo);
            result.setFee(exchangeRequestBody.getAmount().multiply(commission));
            result.setRate(calculateRate(requestedRates, exchangeRequestBody.getFrom(), currencyTo));
            result.setResult(exchangeRequestBody.getAmount().subtract(result.getFee()).multiply(result.getRate()).setScale(2, RoundingMode.HALF_UP));
            exchangeResults.add(result);
        }
        return exchangeResults;
    }
}
