package com.example.flux.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MarketDataController {

    @MessageMapping("/marketdata")
    @SendTo("/topic/marketdata")
    public String sendMarketData(String message) throws Exception {
        // In a real-world application, this method would fetch and return real-time market data.
        return "Real-time market data: " + message;
    }
}
