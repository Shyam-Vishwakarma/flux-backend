package com.example.flux.service;

import com.example.flux.model.QuoteData;
import com.example.flux.repository.QuoteDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteDataService {

    @Autowired
    private QuoteDataRepository quoteDataRepository;

    @Transactional
    public void saveQuoteData(String symbol, String jsonData) {
        QuoteData quoteData = new QuoteData();
        quoteData.setSymbol(symbol);
        quoteData.setJsonData(jsonData);
        quoteDataRepository.save(quoteData);
    }

    public String getQuoteData(String symbol) {
        return quoteDataRepository.findById(symbol)
                .map(QuoteData::getJsonData)
                .orElse(null);
    }
}
