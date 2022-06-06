package com.example.exchangerate.client;

import com.example.exchangerate.model.Gif;

import java.net.URI;

public interface GifClient {

    Gif getRandomGif(String apiKey, String tag);

    byte[] getGif(URI uri);

}
