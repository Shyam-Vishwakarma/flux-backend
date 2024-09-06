package com.example.flux.repository;

import com.example.flux.model.QuoteData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteDataRepository extends JpaRepository<QuoteData, String> {
    QuoteData findBySymbol(String symbol);
}
