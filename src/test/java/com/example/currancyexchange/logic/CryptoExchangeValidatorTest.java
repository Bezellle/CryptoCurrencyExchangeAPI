package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.RequestedRates;
import com.example.currancyexchange.exceptions.WrongCurrencyException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.example.currancyexchange.logic.CryptoExchangeValidator.validateCurrency;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CryptoExchangeValidatorTest {


    @Test
    void testCurrencyValidator(){
        RequestedRates rates = new RequestedRates();
        rates.getRates().put("USD", new BigDecimal("15"));
        rates.getRates().put("BTC", new BigDecimal("10"));
        String from = "USD";
        List<String> currencyToList = Arrays.asList("BTC","");

        Throwable exception = assertThrows(WrongCurrencyException.class, ()->{validateCurrency(rates, from, currencyToList);});
        assertEquals(String.format("Currency not found: %s", currencyToList.get(0)), exception.getMessage());
    }

}