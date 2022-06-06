package com.example.exchangerate.service;


import com.example.exchangerate.client.ExchangeRatesClient;
import com.example.exchangerate.model.ExchangeRates;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Service
public class ExchangeRatesService {

    private final ExchangeRatesClient exchangeRatesClient;
    private final LoadingCache<String, ExchangeRates> cache;

    @Value("${openexchangerates.app.id}")
    private String appId;

    @Autowired
    public ExchangeRatesService(ExchangeRatesClient exchangeRatesClient) {
        this.exchangeRatesClient = exchangeRatesClient;
        this.cache =  Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build(this::getOldRates);
    }

    public Collection<String> getCurrencyCodes() {
        var currentRate = exchangeRatesClient.getCurrentRates(appId);
        if (currentRate.getRates() != null) {
            return currentRate.getRates().keySet();
        } else {
            return null;
        }
    }

    public String getGifTag(String currencyCode) {
        var currentRate = exchangeRatesClient.getCurrentRates(appId);
        var yesterdayDate = LocalDate.now().minusDays(1).toString();
        var oldRates = cache.get(yesterdayDate);
        if ((oldRates.getRates() != null) && (currentRate.getRates() != null)) {
            if (oldRates.getRates().get(currencyCode) > currentRate.getRates().get(currencyCode)) {
                return "broke";
            } else {
                return "rich";
            }
        } else {
            return "broke";
        }
    }

    private ExchangeRates getOldRates(String date) {
        return exchangeRatesClient.getOldRates(date, appId);
    }
}

