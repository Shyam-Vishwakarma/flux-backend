package com.example.flux.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @GetMapping("/currencies")
    public ResponseEntity<String> getCurrencies() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/data/currencyList.json");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()));
        return ResponseEntity.ok(content);
    }

    @GetMapping("/stocks")
    public ResponseEntity<String> getStocks() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/data/stockData.json");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()));
        return ResponseEntity.ok(content);
    }

    @GetMapping("/cryptos")
    public ResponseEntity<String> getCryptos() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/data/cryptoData.json");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()));
        return ResponseEntity.ok(content);
    }
}
