package com.example.flux.service;

import com.example.flux.model.Watchlist;
import com.example.flux.model.WatchlistItem;
import com.example.flux.repository.WatchlistItemRepository;
import com.example.flux.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private WatchlistItemRepository watchlistItemRepository;

    // Create a new watchlist
    public Watchlist createWatchlist(Long userId, String name) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUserId(userId);
        watchlist.setName(name);
        return watchlistRepository.save(watchlist);
    }

    // Add a watchlist item to a watchlist
    public WatchlistItem addWatchlistItem(Long userId, Long watchlistId, WatchlistItem item) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        if (!watchlist.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to access this watchlist");
        }

        // Save watchlist item
        item.setWatchlistId(watchlistId);
        WatchlistItem savedItem = watchlistItemRepository.save(item);

        // Add the item ID to the watchlist
        watchlist.addWatchlistItemId(savedItem.getId());
        watchlistRepository.save(watchlist);

        return savedItem;
    }

    // Get all watchlists for a user
    public List<Watchlist> getWatchlistsByUser(Long userId) {
        return watchlistRepository.findByUserId(userId);
    }

    // Get a specific watchlist by ID
    public Watchlist getWatchlistById(Long watchlistId) {
        return watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));
    }

    // Update a watchlist
    public Watchlist updateWatchlist(Long userId, Long watchlistId, String name) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        if (!watchlist.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to access this watchlist");
        }

        watchlist.setName(name);
        return watchlistRepository.save(watchlist);
    }

    // Delete a watchlist
    public void deleteWatchlist(Long userId, Long watchlistId) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        if (!watchlist.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to access this watchlist");
        }

        watchlistRepository.delete(watchlist);
    }

    // Remove a watchlist item from a watchlist
    public void removeWatchlistItem(Long userId, Long watchlistId, Long itemId) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Watchlist not found"));

        if (!watchlist.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to access this watchlist");
        }

        watchlist.removeWatchlistItemId(itemId);
        watchlistRepository.save(watchlist);

        watchlistItemRepository.deleteById(itemId);
    }
}
