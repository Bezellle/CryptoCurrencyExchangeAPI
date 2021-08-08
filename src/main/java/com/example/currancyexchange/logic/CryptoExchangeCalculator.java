package com.example.currancyexchange.currencyExchange;

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
    private final String defaultExchangeCurrency = "USD";

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
        ArrayList<ExchangeResult> exchangeResults = new ArrayList<>();
        for (String currencyTo : exchangeRequestBody.getTo()) {
            ExchangeResult result = new ExchangeResult();
            result.setCurrency(currencyTo);
            result.setRate(calculateRate(requestedRates, exchangeRequestBody.getFrom(), currencyTo));
            result.setResult(exchangeRequestBody.getAmount().multiply(BigDecimal.ONE.subtract(result.getFee())).multiply(result.getRate()).setScale(2,RoundingMode.HALF_UP));

            exchangeResults.add(result);
        }
        return exchangeResults;
    }
}
