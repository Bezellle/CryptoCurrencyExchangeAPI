package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.CurrancyRequest;
import com.example.currancyexchange.domain.RequestedRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        CurrancyRequest[] currancyRequests = cryptoExchangeClient.getNomicCombinesResponseList();
        RequestedRates requestedRates = cryptoExchangeResponseMapper.createMapWithRates(currancyRequests);

        return cryptoExchangeCalculator.calculateResponseRates(requestedRates, currency, filters);
    }
}

// TODO Validation, Exception