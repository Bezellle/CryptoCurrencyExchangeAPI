package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CryptoExchangeResponse;
import com.example.currancyexchange.domain.currency.CurrencyNomicResponse;
import com.example.currancyexchange.domain.currency.RequestedRates;
import com.example.currancyexchange.domain.exchange.ExchangeRequestBody;
import com.example.currancyexchange.domain.exchange.ExchangeResponse;
import com.example.currancyexchange.domain.exchange.ExchangeResult;
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
        CurrencyNomicResponse[] currencyNomicRespons = cryptoExchangeClient.getAPICurrencyRequest();
        RequestedRates requestedRates = cryptoExchangeResponseMapper.createMapWithRatesFromApiResponse(currencyNomicRespons);
        CryptoExchangeValidator.validateCurrency(requestedRates, currency, filters);
        return cryptoExchangeCalculator.calculateResponseRates(requestedRates, currency, filters);
    }

    public ExchangeResponse getExchangeResponse(ExchangeRequestBody exchangeRequestBody) {
        CryptoExchangeValidator.validateAmount(exchangeRequestBody.getAmount());
        CurrencyNomicResponse[] currencyNomicRespons = cryptoExchangeClient.getAPICurrencyRequest();
        RequestedRates requestedRates = cryptoExchangeResponseMapper.createMapWithRatesFromApiResponse(currencyNomicRespons);
        CryptoExchangeValidator.validateCurrency(requestedRates, exchangeRequestBody.getFrom(), exchangeRequestBody.getTo());
        ArrayList<ExchangeResult> exchangeResult = cryptoExchangeCalculator.calculateExchangeResults(requestedRates, exchangeRequestBody);
        return cryptoExchangeResponseMapper.createExchangeResponse(exchangeRequestBody, exchangeResult);
    }
}
