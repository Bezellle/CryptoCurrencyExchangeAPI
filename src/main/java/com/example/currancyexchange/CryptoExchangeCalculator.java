package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.ExchangeRequestBody;
import com.example.currancyexchange.domain.ExchangeResult;
import com.example.currancyexchange.domain.RequestedRates;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class CryptoExchangeCalculator {
    private String defaultExchangeCurrency = "USD";

    public CryptoExchangeResponse calculateResponseRates(RequestedRates rates, String currency, List<String> filters) {
        CryptoExchangeResponse result = new CryptoExchangeResponse();
        result.setSource(currency);
        if (filters != null && !filters.isEmpty()) {
            for (String filter : filters) {
                BigDecimal rate = calculateRate(rates, currency, filter);
                result.getRates().put(filter, rate);
            }
        } else {
            result.getRates().put(defaultExchangeCurrency, rates.getRates().get(currency));
        }
        return result;
    }

    private BigDecimal calculateRate(RequestedRates rates, String currencyFrom, String currencyTo) {
        return rates.getRates().get(currencyFrom).divide(rates.getRates().get(currencyTo), RoundingMode.HALF_UP);
    }

    public ArrayList<ExchangeResult> calculateExchangeResults(RequestedRates requestedRates, ExchangeRequestBody exchangeRequestBody) {
        ArrayList<ExchangeResult> exchangeResults = new ArrayList<ExchangeResult>();
        for (String currencyTo : exchangeRequestBody.getTo()) {
            ExchangeResult result = new ExchangeResult();
            result.setCurrency(currencyTo);
            result.setRate(calculateRate(requestedRates, exchangeRequestBody.getFrom(), currencyTo));
            result.setResult(exchangeRequestBody.getAmount().multiply(BigDecimal.valueOf(ExchangeResult.getFee())).multiply(result.getRate()));

            exchangeResults.add(result);
        }
        return exchangeResults;
    }
}
