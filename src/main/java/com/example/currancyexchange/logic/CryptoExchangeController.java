package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CryptoExchangeResponse;
import com.example.currancyexchange.domain.exchange.ExchangeRequest;
import com.example.currancyexchange.domain.exchange.ExchangeResponse;
import com.example.currancyexchange.domain.exchange.ExchangeResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class CryptoExchangeController {

    private final CryptoExchangeService cryptoExchangeService;

    public CryptoExchangeController(CryptoExchangeService cryptoExchangeService) {
        this.cryptoExchangeService = cryptoExchangeService;
    }

    @GetMapping("/currencies/{currency}")
    public ResponseEntity<Object> getCurrency(@PathVariable("currency") String currency,
                                              @RequestParam(name = "filter[]", required = false) Optional<List<String>> filters) {

        Map<String, BigDecimal> cryptoExchangeResponse = cryptoExchangeService.getCurrancyResponse(currency, filters.orElse(new ArrayList<>()));
        return new ResponseEntity<>(new CryptoExchangeResponse(currency, cryptoExchangeResponse), OK);
    }

    @PostMapping("/currencies/exchange")
    public ResponseEntity<Object> calculateExchange(@RequestBody ExchangeRequest exchangeRequest) {
        List<ExchangeResult> exchangeResults = cryptoExchangeService.getExchangeResponse(
                exchangeRequest.getFrom(), exchangeRequest.getAmount(), exchangeRequest.getTo());
        ExchangeResponse response = new ExchangeResponse(exchangeRequest.getFrom(), exchangeRequest.getAmount(), exchangeResults);
        return new ResponseEntity<>(response, OK);
    }


}
