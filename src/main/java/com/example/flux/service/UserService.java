package com.example.flux.service;

import com.example.flux.model.User;
import com.example.flux.repository.UserRepository;
import com.example.flux.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user
    public User registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password)); // Store password hash
        user.setEmail(email);
        return userRepository.save(user);
    }

    // Find user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Check if the password matches
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // Add a watchlist to a user
    public User addWatchlistToUser(Long userId, Long watchlistId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (watchlistRepository.existsById(watchlistId)) {
                user.getWatchlistIds().add(watchlistId); // Add watchlist ID to the user's list of watchlist IDs
                return userRepository.save(user);
            }
        }
        throw new RuntimeException("User or Watchlist not found");
    }

    // Remove a watchlist from a user
    public User removeWatchlistFromUser(Long userId, Long watchlistId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getWatchlistIds().remove(watchlistId); // Remove the watchlist ID from the user's list of watchlist IDs
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }
}
