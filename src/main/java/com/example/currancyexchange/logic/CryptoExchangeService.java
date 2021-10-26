package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.RequestedRates;
import com.example.currancyexchange.domain.exchange.ExchangeResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CryptoExchangeService {

    private final CryptoExchangeClient cryptoExchangeClient;
    private final CryptoExchangeCalculator cryptoExchangeCalculator;

    public CryptoExchangeService(CryptoExchangeClient cryptoExchangeClient,
                                 CryptoExchangeCalculator cryptoExchangeCalculator) {
        this.cryptoExchangeClient = cryptoExchangeClient;
        this.cryptoExchangeCalculator = cryptoExchangeCalculator;
    }

    public Map<String, BigDecimal> getCurrancyResponse(String currency, List<String> filters) {
        RequestedRates requestedRates = cryptoExchangeClient.getAPICurrencyRequest();
        CryptoExchangeValidator.validateCurrency(requestedRates, currency, filters);
        return cryptoExchangeCalculator.calculateResponseRates(requestedRates.getRates(), currency, filters);
    }

    public List<ExchangeResult> getExchangeResponse(String from, BigDecimal amount, List<String> to) {
        CryptoExchangeValidator.validateAmount(amount);
        RequestedRates requestedRates = cryptoExchangeClient.getAPICurrencyRequest();
        CryptoExchangeValidator.validateCurrency(requestedRates, from, to);
        return cryptoExchangeCalculator.calculateExchangeResults(requestedRates.getRates(), from, amount, to);
    }
}
