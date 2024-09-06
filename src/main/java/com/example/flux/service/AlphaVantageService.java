package com.example.flux.service;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class AlphaVantageService {
    @Value("${API_KEY_ALPHAVANTAGE}")
    private String alphaVantageApiKey;
    @PostConstruct
    public void init() {
        Config cfg = Config.builder()
                .key(alphaVantageApiKey)
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);
    }
}
