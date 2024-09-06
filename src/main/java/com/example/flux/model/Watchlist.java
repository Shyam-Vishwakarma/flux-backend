package com.example.flux.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @ElementCollection
    @CollectionTable(name = "watchlist_items", joinColumns = @JoinColumn(name = "watchlist_id"))
    @Column(name = "watchlist_item_id")
    private List<Long> watchlistItemIds = new ArrayList<>();

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getWatchlistItemIds() {
        return watchlistItemIds;
    }

    public void setWatchlistItemIds(List<Long> watchlistItemIds) {
        this.watchlistItemIds = watchlistItemIds;
    }

    public void addWatchlistItemId(Long itemId) {
        this.watchlistItemIds.add(itemId);
    }

    public void removeWatchlistItemId(Long itemId) {
        this.watchlistItemIds.remove(itemId);
    }
}
