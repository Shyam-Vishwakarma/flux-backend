package com.example.flux.service;

import com.example.flux.model.WatchlistItem;
import com.example.flux.repository.WatchlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WatchlistItemService {

    @Autowired
    private WatchlistItemRepository watchlistItemRepository;

    public WatchlistItem createWatchlistItem(WatchlistItem watchlistItem) {
        return watchlistItemRepository.save(watchlistItem);
    }

    public Optional<WatchlistItem> getWatchlistItemById(Long watchlistItemId) {
        return watchlistItemRepository.findById(watchlistItemId);
    }

    public WatchlistItem updateWatchlistItem(Long watchlistItemId, WatchlistItem updatedItem) {
        WatchlistItem item = watchlistItemRepository.findById(watchlistItemId)
                .orElseThrow(() -> new RuntimeException("Watchlist Item not found"));
        item.setSymbol(updatedItem.getSymbol());
        item.setType(updatedItem.getType());
        return watchlistItemRepository.save(item);
    }

    public void deleteWatchlistItem(Long watchlistItemId) {
        watchlistItemRepository.deleteById(watchlistItemId);
    }
}
