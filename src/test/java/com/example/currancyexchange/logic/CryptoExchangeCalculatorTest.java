package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CryptoExchangeResponse;
import com.example.currancyexchange.domain.currency.RequestedRates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CryptoExchangeCalculatorTest {

    @Test
    public void calculateCurrencyRateWithNoFilters(){
        CryptoExchangeCalculator calculator = new CryptoExchangeCalculator();
        RequestedRates rates = new RequestedRates();
        String currencyFrom = "BTC";
        List<String> filters = null;

        rates.getRates().put("VEF", BigDecimal.ONE);
        rates.getRates().put("VEF1", BigDecimal.ZERO);
        rates.getRates().put("VEF2", null);
        rates.getRates().put("VEF3", new BigDecimal("0.000005"));
        rates.getRates().put("VEF4", new BigDecimal("0.000000"));
        rates.getRates().put("VEF5", new BigDecimal("-0.000005"));
        rates.getRates().put("BTC", BigDecimal.ONE);

        CryptoExchangeResponse response = calculator.calculateResponseRates(rates, currencyFrom, filters);

        assertEquals(3, response.getRates().size());
    }

    @Test
    public void calculateCurrencyRateResponseWithFilters(){
        CryptoExchangeCalculator calculator = new CryptoExchangeCalculator();
        RequestedRates rates = new RequestedRates();
        String currencyFrom = "BTC";
        List<String> filters = new ArrayList<>();
        filters.add("VEF");
        filters.add("BTC");
        filters.add(null);
        filters.add("");

        rates.getRates().put("VEF", BigDecimal.ONE);
        rates.getRates().put("VEF1", BigDecimal.ZERO);
        rates.getRates().put("VEF2", null);
        rates.getRates().put("VEF3", new BigDecimal("0.000005"));
        rates.getRates().put("VEF4", new BigDecimal("0.000000"));
        rates.getRates().put("VEF5", new BigDecimal("-0.000005"));
        rates.getRates().put("BTC", BigDecimal.ONE);

        CryptoExchangeResponse response = calculator.calculateResponseRates(rates, currencyFrom, filters);

        assertEquals(2, response.getRates().size());
    }

    @Test
    public void testCalculatedResults(){
        CryptoExchangeCalculator calculator = new CryptoExchangeCalculator();
        RequestedRates rates = new RequestedRates();
        String currencyFrom = "BTC";
        List<String> filters = new ArrayList<>();

        rates.getRates().put("VEF", BigDecimal.ONE);
        rates.getRates().put("VEF3", new BigDecimal("0.0005"));
        rates.getRates().put("VEF6", new BigDecimal("5.0"));
        rates.getRates().put("BTC", BigDecimal.ONE);

        CryptoExchangeResponse response = calculator.calculateResponseRates(rates, currencyFrom, filters);

        assertEquals(new BigDecimal("1.00000000"), response.getRates().get("VEF"));
        assertEquals(new BigDecimal("1.00000000"), response.getRates().get("BTC"));
        assertEquals(new BigDecimal("2000.00000000"), response.getRates().get("VEF3"));
        assertEquals(new BigDecimal("0.20000000"), response.getRates().get("VEF6"));
    }
}