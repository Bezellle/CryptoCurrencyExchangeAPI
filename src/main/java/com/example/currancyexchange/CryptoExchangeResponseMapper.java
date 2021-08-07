package com.example.currancyexchange;

import com.example.currancyexchange.domain.CryptoExchangeResponse;
import com.example.currancyexchange.domain.NomicCombinedResponse;
import com.example.currancyexchange.domain.RatesCryptoExchangeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CryptoExchangeResponseMapper {

    public CryptoExchangeResponse getResponse(NomicCombinedResponse nomicCombinedResponse, List<String> filters){
        CryptoExchangeResponse cryptoExchangeResponse = new CryptoExchangeResponse();
        RatesCryptoExchangeResponse ratesCryptoExchangeResponse = new RatesCryptoExchangeResponse();

        cryptoExchangeResponse.setSource(nomicCombinedResponse.getCombinedResponse().get(0).getId());

        for(String filter: filters){
            Integer numberOfRates = 0;
            ratesCryptoExchangeResponse.add(filter,
                    nomicCombinedResponse.getCombinedResponse().get(numberOfRates).getPrice());
            numberOfRates++;
        }
        cryptoExchangeResponse.setRates(ratesCryptoExchangeResponse);
        return cryptoExchangeResponse;
    }
}
