package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.exchange.ExchangeResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Component
public class CryptoExchangeCalculator {
    private final BigDecimal commission = new BigDecimal("0.01");


    public Map<String, BigDecimal> calculateResponseRates(Map<String, BigDecimal> rates, String currencyFrom, List<String> filters) {
        BigDecimal sourceCurrencyToUsdRate = rates.get(currencyFrom);
        return getFilteredRates(rates, filters)
                .map(calculateRate(sourceCurrencyToUsdRate))
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

    private Stream<Entry<String, BigDecimal>> getFilteredRates(Map<String, BigDecimal> rates, List<String> filters) {
        return filters.isEmpty() ? rates.entrySet().stream().filter(entry -> entry.getValue().equals(BigDecimal.ZERO)) : rates.entrySet().stream().filter(entry -> filters.contains(entry.getKey())).filter(entry -> entry.getValue().equals(BigDecimal.ZERO));
    }

    private Function<Entry<String, BigDecimal>, SimpleEntry<String, BigDecimal>> calculateRate(BigDecimal sourceCurrencyToUsdRate) {
        return e -> new SimpleEntry<>(e.getKey(), calculateRate(sourceCurrencyToUsdRate, e.getValue()));
    }

    private BigDecimal calculateRate(BigDecimal sourceCurrencyToUsdRate, BigDecimal targetCurrencyUsdRate) {
        return Optional.of(sourceCurrencyToUsdRate.divide(targetCurrencyUsdRate, 8, RoundingMode.HALF_UP)).orElse(BigDecimal.ZERO);
    }

    public List<ExchangeResult> calculateExchangeResults(Map<String, BigDecimal> rates, String sourceCurrency, BigDecimal amount, List<String> targetCurrencies) {
        BigDecimal sourceCurrencyUsdRate = rates.get(sourceCurrency);

        return getFilteredRates(rates, targetCurrencies)
                .map(entry -> new ExchangeResult(entry.getKey(),
                        calculateRate(sourceCurrencyUsdRate, entry.getValue()),
                        calculateFee(amount),
                        calculateExchangeResult(amount, calculateFee(amount), calculateRate(sourceCurrencyUsdRate, entry.getValue()))))
                .collect(Collectors.toList());
    }

    private BigDecimal calculateFee(BigDecimal amount) {
        return amount.multiply(commission);
    }

    private BigDecimal calculateExchangeResult(BigDecimal amount, BigDecimal fee, BigDecimal rate) {
        return amount.subtract(fee).multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
