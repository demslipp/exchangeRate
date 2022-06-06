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
    public void currencyCodesIsNull() {
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
    public void richGifTag() {
        String code = "RUB";
        String rich = "rich";
        when(exchangeRatesClient.getCurrentRates(any())).thenReturn(currentExchangeRates());
        when(exchangeRatesClient.getOldRates(any(), any())).thenReturn(oldExchangeRates());
        var value = assertDoesNotThrow(() -> exchangeRatesService.getGifTag(code));
        assertEquals(rich, value);
    }

    @Test
    public void richGifTagWithEqual() {
        String code = "KPW";
        String rich = "rich";
        when(exchangeRatesClient.getCurrentRates(any())).thenReturn(currentExchangeRates());
        when(exchangeRatesClient.getOldRates(any(), any())).thenReturn(oldExchangeRates());
        var value = assertDoesNotThrow(() -> exchangeRatesService.getGifTag(code));
        assertEquals(rich, value);
    }

    @Test
    public void brokeGifTag() {
        String code = "EUR";
        String rich = "broke";
        when(exchangeRatesClient.getCurrentRates(any())).thenReturn(currentExchangeRates());
        when(exchangeRatesClient.getOldRates(any(), any())).thenReturn(oldExchangeRates());
        var value = assertDoesNotThrow(() -> exchangeRatesService.getGifTag(code));
        assertEquals(rich, value);
    }

    private ExchangeRates oldExchangeRates() {
        var timestamp = 1654448428;
        var base = "USD";
        Map<String, Double> oldRates = Map.of("RUB", 1.3, "EUR", 0.9, "KPW", 900.0);
        return new ExchangeRates(timestamp, base, oldRates);
    }

    private ExchangeRates currentExchangeRates() {
        var timestamp = 1654448428;
        var base = "USD";
        Map<String, Double> currentRates = Map.of("RUB", 1.5, "EUR", 0.89, "KPW", 900.0);
        return new ExchangeRates(timestamp, base, currentRates);
    }


}
