package com.example.flux.service;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class AlphaVantageService {
    private String alphaVantageApiKey = "8XDOS573QPTOK73I";
    @PostConstruct
    public void init() {
        Config cfg = Config.builder()
                .key(alphaVantageApiKey)
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);
    }
}
