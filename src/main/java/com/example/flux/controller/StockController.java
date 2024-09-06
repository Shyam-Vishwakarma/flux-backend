package com.example.flux.controller;

import com.example.flux.service.StockService;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{symbol}/intraday")
    public TimeSeriesResponse getIntradayData(@PathVariable String symbol) {
        return stockService.getIntradayData(symbol);
    }

    @GetMapping("/{symbol}/daily")
    public TimeSeriesResponse getDailyData(@PathVariable String symbol) {
        return stockService.getDailyData(symbol);
    }

    @GetMapping("/{symbol}/weekly")
    public TimeSeriesResponse getWeeklyData(@PathVariable String symbol) {
        return stockService.getWeeklyData(symbol);
    }

    @GetMapping("/{symbol}/monthly")
    public TimeSeriesResponse getMonthlyData(@PathVariable String symbol) {
        return stockService.getMonthlyData(symbol);
    }

    @GetMapping("/{symbol}/quote")
    public String getQuoteData(@PathVariable String symbol) {
        return stockService.getQuoteData(symbol);
    }
}
