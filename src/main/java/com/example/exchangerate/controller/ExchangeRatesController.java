package com.example.exchangerate.controller;

import com.example.exchangerate.service.ExchangeRatesService;
import com.example.exchangerate.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class ExchangeRatesController {

    private final GifService gifService;
    private final ExchangeRatesService exchangeRatesService;
    @Value("${giphy.rich}")
    private String richGif;
    @Value("${giphy.broke}")
    private String brokeGif;

    @Autowired
    public ExchangeRatesController(GifService gifService, ExchangeRatesService exchangeRatesService) {
        this.gifService = gifService;
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/codes")
    public Collection<String> getCodes() {

        return exchangeRatesService.getCurrencyCodes();
    }


    @GetMapping(value = "/gifs/{code}", produces = "application/json")
    public ResponseEntity<byte[]> getExchangeRateGif(@PathVariable String code) {
        if (code != null) {
            String gifTag = exchangeRatesService.getGifTag(code);
            var gifBytes = gifService.getGif(gifTag.equals("rich") ? this.richGif : this.brokeGif);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_GIF)
                    .body(gifBytes);
        } else {
            return null;
        }
    }
}
