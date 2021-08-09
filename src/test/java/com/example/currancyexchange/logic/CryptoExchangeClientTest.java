package com.example.currancyexchange.logic;

import com.example.currancyexchange.domain.currency.CurrancyNomicResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CryptoExchangeClientTest {

    public RestTemplate mockedRestTemplate = Mockito.mock(RestTemplate.class);

    private String requestURL = "testUrl";
    private String key = "testKey";


    @Test
    public void testSendingRequestsToAPI(){

        CurrancyNomicResponse[] responses = new CurrancyNomicResponse[]{};

        Mockito.when(mockedRestTemplate.getForObject(requestURL+key, CurrancyNomicResponse[].class)).thenReturn(responses);

        CryptoExchangeClient cryptoExchangeClient = new CryptoExchangeClient(mockedRestTemplate);
        cryptoExchangeClient.setKey(key);
        cryptoExchangeClient.setNomicsAPI_URL(requestURL);

        CurrancyNomicResponse[] result = cryptoExchangeClient.getAPICurrencyRequest();
        assertTrue(responses.length == 0);
    }

}