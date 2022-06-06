package com.example.exchangerate.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gif {
    private Data data;

    public String getUrl(){
        return data.images().original().get("url");
    }

    record Data(
            Images images
    ) {
    }

    record Images(
            Map<String, String> original
    ) {
    }

}

