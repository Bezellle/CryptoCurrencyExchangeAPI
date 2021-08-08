package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CryptoExchangeResponse;
import com.example.currancyexchange.domain.exchange.ExchangeRequestBody;
import com.example.currancyexchange.domain.exchange.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptoExchangeController {

    @Autowired
    private final CryptoExchangeService cryptoExchangeService;

    public CryptoExchangeController(CryptoExchangeService cryptoExchangeService) {
        this.cryptoExchangeService = cryptoExchangeService;
    }

    @GetMapping("/currencies/{currency}")
    public ResponseEntity<Object> getCurrency(@PathVariable("currency") String currency,
                                              @RequestParam(name = "filter[]", required = false) List<String> filters) {

        CryptoExchangeResponse cryptoExchangeResponse = cryptoExchangeService.getCurrancyResponse(currency,filters);
        return new ResponseEntity<>( cryptoExchangeResponse, HttpStatus.OK);
    }

    @PostMapping("/currencies/exchange")
    public ResponseEntity<Object> calculateExchange(@RequestBody ExchangeRequestBody exchangeRequestBody){
        ExchangeResponse exchangeResponse = cryptoExchangeService.getExchangeResponse(exchangeRequestBody);
        return new ResponseEntity<>(exchangeResponse, HttpStatus.OK);
    }


}
