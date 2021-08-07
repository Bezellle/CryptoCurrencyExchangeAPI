package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.NomicCombinedResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CryptoExchangeService {

    private final CryptoExchangeClient cryptoExchangeClient;
    private final CryptoExchangeResponseMapper cryptoExchangeResponseMapper;

    public CryptoExchangeService(CryptoExchangeClient cryptoExchangeClient, CryptoExchangeResponseMapper cryptoExchangeResponseMapper) {
        this.cryptoExchangeClient = cryptoExchangeClient;
        this.cryptoExchangeResponseMapper = cryptoExchangeResponseMapper;
    }

    public CryptoExchangeResponse getCurrancyResponse(String currency, List<String> filters){
        NomicCombinedResponse nomicCombinedResponse = cryptoExchangeClient.getNomicCombinesResponseList(currency, filters);
        return cryptoExchangeResponseMapper.getResponse(nomicCombinedResponse, filters);
    }
}
