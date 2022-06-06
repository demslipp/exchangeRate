package com.example.exchangerate;

import com.example.exchangerate.client.ExchangeRatesClient;
import com.example.exchangerate.model.ExchangeRates;
import com.example.exchangerate.service.ExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ExchangeRatesServiceTest {

    @MockBean
    private ExchangeRatesClient exchangeRatesClient;

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Test
    void currencyCodesIsNull() {
        ExchangeRates getCurrentRatesResponse = new ExchangeRates();
        when(exchangeRatesClient.getCurrentRates(any())).thenReturn(getCurrentRatesResponse);
        var result = assertDoesNotThrow(exchangeRatesService::getCurrencyCodes);

        assertNull(result);
    }

    @Test
    public void currencyCodesNotNull() {
        Integer timestamp = 1654448428;
        String base = "USD";
        Map<String, Double> currentRates = Map.of("RUB", 1.3, "BTC", 120.6, "USD", 2.0);
        ExchangeRates getCurrentRatesResponse = new ExchangeRates(timestamp, base, currentRates);
        when(exchangeRatesClient.getCurrentRates(any())).thenReturn(getCurrentRatesResponse);

        var result = assertDoesNotThrow(exchangeRatesService::getCurrencyCodes);

        assertEquals(currentRates.keySet(), result);
    }

    @Test
    public void gifTag() {
        Integer timestamp = 1654448428;
        String base = "USD";
        Map<String, Double> currentRates = Map.of("RUB", 1.3, "BTC", 120.6, "USD", 2.0);
        ExchangeRates exchangeRatesResponse = new ExchangeRates(timestamp, base, currentRates);
        when(exchangeRatesClient.getCurrentRates(any())).thenReturn(exchangeRatesResponse);
        when(exchangeRatesResponse.getRates()).thenReturn(currentRates);
        var value = assertDoesNotThrow( ()-> exchangeRatesService.getGifTag(base));
        assertEquals(currentRates.keySet().toArray()[0], value);
    }


}
