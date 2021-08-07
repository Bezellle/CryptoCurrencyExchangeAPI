package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.CurrancyRequest;
import com.example.currancyexchange.domain.NomicCombinedResponse;
import com.example.currancyexchange.domain.RatesCryptoExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoExchangeService {

    private final CryptoExchangeClient cryptoExchangeClient;
    private final CryptoExchangeResponseMapper cryptoExchangeResponseMapper;

    public CryptoExchangeService(CryptoExchangeClient cryptoExchangeClient, CryptoExchangeResponseMapper cryptoExchangeResponseMapper) {
        this.cryptoExchangeClient = cryptoExchangeClient;
        this.cryptoExchangeResponseMapper = cryptoExchangeResponseMapper;
    }

    public CryptoExchangeResponse getCurrancyResponse(String currency,List<String> filters){
        CurrancyRequest[] currancyRequests = cryptoExchangeClient.getNomicCombinesResponseList();
        RatesCryptoExchangeResponse ratesCryptoExchangeResponse = cryptoExchangeResponseMapper.createMapWithRates(currancyRequests);
        return cryptoExchangeResponseMapper.getResponse(currancyRequests, filters);
    }
}

// TODO Validation, Exception, Calculator