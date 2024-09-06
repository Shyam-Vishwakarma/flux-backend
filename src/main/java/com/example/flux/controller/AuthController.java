package com.example.flux.controller;

import com.example.flux.model.User;
import com.example.flux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public User register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return userService.registerUser(username, password, email);
    }

    // Login user
    @PostMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user != null && userService.checkPassword(password, user.getPasswordHash())) {
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }

    // Add a watchlist to a user
    @PostMapping("/{userId}/add/watchlists")
    public User addWatchlist(@PathVariable Long userId, @RequestParam Long watchlistId) {
        return userService.addWatchlistToUser(userId, watchlistId);
    }

    // Remove a watchlist from a user
    @DeleteMapping("/{userId}/delete/watchlists")
    public User removeWatchlist(@PathVariable Long userId, @RequestParam Long watchlistId) {
        return userService.removeWatchlistFromUser(userId, watchlistId);
    }
}
