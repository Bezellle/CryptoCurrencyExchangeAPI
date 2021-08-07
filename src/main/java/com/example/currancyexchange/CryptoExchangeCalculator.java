package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.RequestedRates;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CryptoExchangeCalculator {
    private String defaultExchangeCurrency = "USD";

    public CryptoExchangeResponse calculateResponseRates(RequestedRates rates, String currency, List<String> filters) {
        CryptoExchangeResponse result = new CryptoExchangeResponse();
        result.setSource(currency);
        if (filters != null && !filters.isEmpty()) {
            for (String filter : filters) {
                BigDecimal rate = rates.getRates().get(currency).divide(rates.getRates().get(filter), RoundingMode.HALF_UP);
                result.getRates().put(filter, rate);
            }
        } else {
            result.getRates().put(defaultExchangeCurrency, rates.getRates().get(currency));
        }
        return result;
    }
}
