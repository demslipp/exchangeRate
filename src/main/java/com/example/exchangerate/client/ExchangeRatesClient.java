package com.example.exchangerate.client;

import com.example.exchangerate.model.ExchangeRates;

public interface ExchangeRatesClient {

    ExchangeRates getCurrentRates(String appId);

    ExchangeRates getOldRates(String date, String appId);
}
