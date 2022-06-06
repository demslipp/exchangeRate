//package com.example.exchangerate;
//
//import com.example.exchangerate.client.GifClient;
//import com.example.exchangerate.model.Gif;
//import com.example.exchangerate.service.GifService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.net.URI;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class GifServiceTest {
//
//    @Autowired
//    GifService gifService;
//    @MockBean
//    private GifClient gifClient;
//
//    @Test
//    void urlCreating() {
//        URI responseUri;
//        Gif gifModel = new Gif();
//        String url = "https://media3.giphy.com/media/iJUdMfiwyNdUiAPLPH//giphy.mp4?cid=b5c715b9772563673fcdd55d2875bf67d450fcb71ffff35c&rid=giphy.mp4&ct=g%22";
//        String tag = "rich";
//        when(gifClient.getRandomGif(any(), any())).thenReturn(gifModel);
//        var result = assertDoesNotThrow(gifModel.getUrl());
//        assertEquals
//    }
//
//
//}
