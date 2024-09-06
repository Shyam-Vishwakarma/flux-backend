package com.example.flux.service;

import com.example.flux.model.QuoteData;
import com.example.flux.repository.QuoteDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class StockService {
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        // Initialization logic if needed
    }

    public TimeSeriesResponse getIntradayData(String symbol) {
        return AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol(symbol)
                .interval(Interval.FIVE_MIN)
                .outputSize(OutputSize.COMPACT)
                .fetchSync();
    }

    public TimeSeriesResponse getDailyData(String symbol) {
        return AlphaVantage.api()
                .timeSeries()
                .daily()
                .forSymbol(symbol)
                .outputSize(OutputSize.COMPACT)
                .fetchSync();
    }

    public TimeSeriesResponse getWeeklyData(String symbol) {
        return AlphaVantage.api()
                .timeSeries()
                .weekly()
                .forSymbol(symbol)
                .fetchSync();
    }

    public TimeSeriesResponse getMonthlyData(String symbol) {
        return AlphaVantage.api()
                .timeSeries()
                .monthly()
                .forSymbol(symbol)
                .fetchSync();
    }

@Autowired
private QuoteDataRepository quoteDataRepository;
    @Value("${API_KEY_FINANCIAL_MODELING_PREMIUM}")
    private String financialModelingApiKey;

    public String getQuoteData(String symbol) {
        QuoteData existingData = quoteDataRepository.findBySymbol(symbol);
        LocalDateTime currentTime = LocalDateTime.now();
        Duration fourHours = Duration.ofHours(4);

        if (existingData != null) {
            LocalDateTime dataTimestamp = existingData.getCreatedDate();
            if (dataTimestamp != null && Duration.between(dataTimestamp, currentTime).compareTo(fourHours) < 0) {
                return existingData.getJsonData();
            }
        }

        String url = String.format("https://financialmodelingprep.com/api/v3/quote-order/%s?apikey=%s", symbol, financialModelingApiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String apiResponse = response.getBody();

        QuoteData newQuoteData = new QuoteData();
        newQuoteData.setSymbol(symbol);
        newQuoteData.setJsonData(apiResponse);
        newQuoteData.setCreatedDate(currentTime);
        quoteDataRepository.save(newQuoteData);

        return apiResponse;
    }

}
