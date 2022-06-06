package com.example.exchangerate;

import com.example.exchangerate.client.GifClient;
import com.example.exchangerate.model.Gif;
import com.example.exchangerate.service.GifService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GifServiceTest {

    @Autowired
    GifService gifService;
    @MockBean
    private GifClient gifClient;

    @Test
    void getGif() {
        Gif randomGifResponse = new GifTest();
        String tag = "rich";
        var gifResponse = new byte[]{0x12, 0x22, 0x40};
        when(gifClient.getRandomGif(any(), any()))
                .thenReturn(randomGifResponse);
        when(gifClient.getGif(any()))
                .thenReturn(gifResponse);
        var result = assertDoesNotThrow(() -> gifService.getGif(tag));
        assertEquals(gifResponse, result);
    }

    static class GifTest extends Gif {
        @Override
        public String getUrl() {
            return "giveUrl";
        }

    }


}
