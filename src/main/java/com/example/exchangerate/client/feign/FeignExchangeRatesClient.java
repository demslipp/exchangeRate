package com.example.exchangerate.client.feign;


import com.example.exchangerate.client.ExchangeRatesClient;
import com.example.exchangerate.model.ExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ExchangeRateClient", url = "${openexchangerates.url.general}")
public interface FeignExchangeRatesClient extends ExchangeRatesClient {

    @Override
    @GetMapping("/latest.json")
    ExchangeRates getCurrentRates(@RequestParam("app_id") String appId);

    @Override
    @GetMapping("/historical/{date}.json")
    ExchangeRates getOldRates(
            @PathVariable String date,
            @RequestParam("app_id") String appId
    );
}
