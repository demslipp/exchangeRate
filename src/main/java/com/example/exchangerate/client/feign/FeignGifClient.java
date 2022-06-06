package com.example.exchangerate.client.feign;

import com.example.exchangerate.client.GifClient;
import com.example.exchangerate.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "gifClient", url = "${giphy.url.general}")
public interface FeignGifClient extends GifClient {

    @Override
    @GetMapping("/random")
    Gif getRandomGif(
            @RequestParam("api_key") String apiKey,
            @RequestParam("tag") String tag);
    @GetMapping
    byte[] getGif(URI url);
}
