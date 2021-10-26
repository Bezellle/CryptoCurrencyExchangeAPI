package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CryptoExchangeResponse;
import com.example.currancyexchange.domain.currency.RequestedRates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CryptoExchangeCalculatorTest {

    @Test
    public void calculateCurrencyRateWithNoFilters(){
        CryptoExchangeCalculator calculator = new CryptoExchangeCalculator();
        HashMap<String, BigDecimal> rates = new HashMap<>();
        String currencyFrom = "BTC";
        List<String> filters = new ArrayList<>();

        rates.put("VEF", BigDecimal.ONE);
        rates.put("VEF1", BigDecimal.ZERO);
        rates.put("VEF2", null);
        rates.put("VEF3", new BigDecimal("0.000005"));
        rates.put("VEF4", new BigDecimal("0.000000"));
        rates.put("VEF5", new BigDecimal("-0.000005"));
        rates.put("BTC", BigDecimal.ONE);

        Map<String, BigDecimal> response = calculator.calculateResponseRates(rates, currencyFrom, filters);

        assertEquals(3, response.size());
    }

    @Test
    public void calculateCurrencyRateResponseWithFilters(){
        CryptoExchangeCalculator calculator = new CryptoExchangeCalculator();
        HashMap<String, BigDecimal> rates = new HashMap<>();
        String currencyFrom = "BTC";
        List<String> filters = new ArrayList<>();
        filters.add("VEF");
        filters.add("BTC");
        filters.add(null);
        filters.add("");

        rates.put("VEF", BigDecimal.ONE);
        rates.put("VEF1", BigDecimal.ZERO);
        rates.put("VEF2", null);
        rates.put("VEF3", new BigDecimal("0.000005"));
        rates.put("VEF4", new BigDecimal("0.000000"));
        rates.put("VEF5", new BigDecimal("-0.000005"));
        rates.put("BTC", BigDecimal.ONE);

        Map<String, BigDecimal> response = calculator.calculateResponseRates(rates, currencyFrom, filters);

        assertEquals(2, response.size());
    }

    @Test
    public void testCalculatedResults(){
        CryptoExchangeCalculator calculator = new CryptoExchangeCalculator();
        HashMap<String, BigDecimal> rates = new HashMap<>();
        String currencyFrom = "BTC";
        List<String> filters = new ArrayList<>();

        rates.put("VEF", BigDecimal.ONE);
        rates.put("VEF3", new BigDecimal("0.0005"));
        rates.put("VEF6", new BigDecimal("5.0"));
        rates.put("BTC", BigDecimal.ONE);

        Map<String, BigDecimal> response = calculator.calculateResponseRates(rates, currencyFrom, filters);

        assertEquals(new BigDecimal("1.00000000"), response.get("VEF"));
        assertEquals(new BigDecimal("1.00000000"), response.get("BTC"));
        assertEquals(new BigDecimal("2000.00000000"), response.get("VEF3"));
        assertEquals(new BigDecimal("0.20000000"), response.get("VEF6"));
    }
}