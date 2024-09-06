package com.example.flux.repository;

import com.example.flux.model.WatchlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistItemRepository extends JpaRepository<WatchlistItem, Long> {
}
