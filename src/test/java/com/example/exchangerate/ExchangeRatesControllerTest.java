package com.example.exchangerate;


import com.example.exchangerate.client.ExchangeRatesClient;
import com.example.exchangerate.client.GifClient;
import com.example.exchangerate.model.ExchangeRates;
import com.example.exchangerate.model.Gif;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRatesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GifClient gifClient;
    @MockBean
    private ExchangeRatesClient exchangeRatesClient;


    @Test
    public void richGif() throws Exception {
        Gif getRandomGifResponse = new TestGif();
        var getGifResponse = new byte[]{0x12, 0x22, 0x40};
        when(gifClient.getRandomGif(any(), any()))
                .thenReturn(getRandomGifResponse);
        when(gifClient.getGif(any()))
                .thenReturn(getGifResponse);
        when(exchangeRatesClient.getOldRates(any(), any()))
                .thenReturn(oldExchangeRates());
        when(exchangeRatesClient.getCurrentRates(any()))
                .thenReturn(currentExchangeRates());

        mockMvc.perform(get("/gifs/RUB")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.IMAGE_GIF))
                .andExpect(content().bytes(getGifResponse));
    }
    @Test
    public void brokeGif() throws Exception {
        Gif getRandomGifResponse = new TestGif();
        var getGifResponse = new byte[]{0x12, 0x22, 0x40};
        when(gifClient.getRandomGif(any(), any()))
                .thenReturn(getRandomGifResponse);
        when(gifClient.getGif(any()))
                .thenReturn(getGifResponse);
        when(exchangeRatesClient.getOldRates(any(), any()))
                .thenReturn(oldExchangeRates());
        when(exchangeRatesClient.getCurrentRates(any()))
                .thenReturn(currentExchangeRates());

        mockMvc.perform(get("/gifs/BTC")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.IMAGE_GIF))
                .andExpect(content().bytes(getGifResponse));
    }



    private ExchangeRates oldExchangeRates() {
        var timestamp = 1654448428;
        var base = "USD";
        Map<String, Double> oldRates = Map.of("RUB", 1.3, "BTC", 120.6, "KPW", 900.0);
        return new ExchangeRates(timestamp, base, oldRates);
    }

    private ExchangeRates currentExchangeRates() {
        var timestamp = 1654448428;
        var base = "USD";
        Map<String, Double> currentRates = Map.of("RUB", 1.5, "BTC", 100.0, "KPW", 900.0);
        return new ExchangeRates(timestamp, base, currentRates);
    }


    class TestGif extends Gif {

        @Override
        public String getUrl() {
            return "giveUrl";
        }
    }
}
