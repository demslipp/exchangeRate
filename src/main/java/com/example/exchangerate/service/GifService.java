package com.example.exchangerate.service;


import com.example.exchangerate.client.GifClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class GifService {

    private GifClient gifClient;
    @Value("${giphy.api.key}")
    private String apiKey;

    @Autowired
    public GifService(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    public byte[] getGif(String tag) {
        URI uri;
        var data = gifClient.getRandomGif(this.apiKey, tag);
        var url = data.getUrl();
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException("couldn't parse url: " + url, e);
        }
        return gifClient.getGif(uri);
    }


}
