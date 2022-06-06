package com.example.exchangerate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeRates {

    private Integer timestamp;
    private String base;
    private Map<String, Double> rates;
}
