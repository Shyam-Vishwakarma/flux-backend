package com.example.flux.controller;

import com.example.flux.model.Watchlist;
import com.example.flux.model.WatchlistItem;
import com.example.flux.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlists")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    // Create a new watchlist
    @PostMapping("/{userId}/create")
    public Watchlist createWatchlist(@PathVariable Long userId, @RequestParam String name) {
        return watchlistService.createWatchlist(userId, name);
    }

    // Add a watchlist item to a user's watchlist
    @PostMapping("/{userId}/{watchlistId}/item")
    public WatchlistItem addWatchlistItem(@PathVariable Long userId, @PathVariable Long watchlistId, @RequestBody WatchlistItem item) {
        return watchlistService.addWatchlistItem(userId, watchlistId, item);
    }

    // Get all watchlists for a user
    @GetMapping("/user/{userId}")
    public List<Watchlist> getWatchlistsByUser(@PathVariable Long userId) {
        return watchlistService.getWatchlistsByUser(userId);
    }

    // Get a specific watchlist by ID
    @GetMapping("/get/{watchlistId}")
    public Watchlist getWatchlistById(@PathVariable Long watchlistId) {
        return watchlistService.getWatchlistById(watchlistId);
    }

    // Update a watchlist
    @PutMapping("/{userId}/{watchlistId}")
    public Watchlist updateWatchlist(@PathVariable Long userId, @PathVariable Long watchlistId, @RequestParam String name) {
        return watchlistService.updateWatchlist(userId, watchlistId, name);
    }

    // Delete a watchlist
    @DeleteMapping("/{userId}/{watchlistId}")
    public void deleteWatchlist(@PathVariable Long userId, @PathVariable Long watchlistId) {
        watchlistService.deleteWatchlist(userId, watchlistId);
    }

    // Remove a watchlist item from a watchlist
    @DeleteMapping("/{userId}/{watchlistId}/items/{itemId}")
    public void removeWatchlistItem(@PathVariable Long userId, @PathVariable Long watchlistId, @PathVariable Long itemId) {
        watchlistService.removeWatchlistItem(userId, watchlistId, itemId);
    }
}
