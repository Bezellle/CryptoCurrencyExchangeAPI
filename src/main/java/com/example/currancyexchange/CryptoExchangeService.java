package com.example.currancyexchange;

import com.example.currancyexchange.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoExchangeService {

    private final CryptoExchangeClient cryptoExchangeClient;
    private final CryptoExchangeResponseMapper cryptoExchangeResponseMapper;
    private final CryptoExchangeCalculator cryptoExchangeCalculator;

    public CryptoExchangeService(CryptoExchangeClient cryptoExchangeClient,
                                 CryptoExchangeResponseMapper cryptoExchangeResponseMapper,
                                 CryptoExchangeCalculator cryptoExchangeCalculator) {
        this.cryptoExchangeClient = cryptoExchangeClient;
        this.cryptoExchangeResponseMapper = cryptoExchangeResponseMapper;
        this.cryptoExchangeCalculator = cryptoExchangeCalculator;
    }

    public CryptoExchangeResponse getCurrancyResponse(String currency, List<String> filters) {
        CurrancyRequest[] currancyRequests = cryptoExchangeClient.getAPICurrencyRequest();
        RequestedRates requestedRates = cryptoExchangeResponseMapper.createMapWithRates(currancyRequests);
        CryptoExchangeValidator.validateCurrency(requestedRates, currency, filters);
        return cryptoExchangeCalculator.calculateResponseRates(requestedRates, currency, filters);
    }

    public ExchangeResponse getExchangeResponse(ExchangeRequestBody exchangeRequestBody) {
        CurrancyRequest[] currancyRequests = cryptoExchangeClient.getAPICurrencyRequest();
        RequestedRates requestedRates = cryptoExchangeResponseMapper.createMapWithRates(currancyRequests);
        CryptoExchangeValidator.validateCurrency(requestedRates, exchangeRequestBody.getFrom(), exchangeRequestBody.getTo());
        ArrayList<ExchangeResult> exchangeResult = cryptoExchangeCalculator.calculateExchangeResults(requestedRates, exchangeRequestBody);
        return cryptoExchangeResponseMapper.createExchangeResponse(exchangeRequestBody, exchangeResult);
    }
}

// TODO Testing
// TODO Packages
// TODO Name Refactor