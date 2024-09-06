package com.example.flux.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String passwordHash;

    @Email
    private String email;

    @ElementCollection
    @CollectionTable(name = "user_watchlists", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "watchlist_id")
    private List<Long> watchlistIds = new ArrayList<>();

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getWatchlistIds() {
        return watchlistIds;
    }

    public void setWatchlistIds(List<Long> watchlistIds) {
        this.watchlistIds = watchlistIds;
    }
}
