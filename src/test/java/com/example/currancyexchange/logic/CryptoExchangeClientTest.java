package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CurrencyNomicResponse;
import com.example.currancyexchange.domain.currency.RequestedRates;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CryptoExchangeClientTest {

    public RestTemplate mockedRestTemplate = Mockito.mock(RestTemplate.class);

    private String requestURL = "testUrl";
    private String key = "testKey";


    @Test
    public void testSendingRequestsToAPI(){

        CurrencyNomicResponse[] responses = new CurrencyNomicResponse[]{};

        Mockito.when(mockedRestTemplate.getForObject(requestURL+key, CurrencyNomicResponse[].class)).thenReturn(responses);

        CryptoExchangeClient cryptoExchangeClient = new CryptoExchangeClient(mockedRestTemplate);
        cryptoExchangeClient.setKey(key);
        cryptoExchangeClient.setNomicsAPI_URL(requestURL);

        RequestedRates result = cryptoExchangeClient.getAPICurrencyRequest();
        assertTrue(responses.length == 0);
    }

}