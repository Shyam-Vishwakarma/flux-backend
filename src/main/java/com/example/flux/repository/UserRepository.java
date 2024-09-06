package com.example.flux.repository;

import com.example.flux.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by their username
    User findByUsername(String username);

    // Find a user by their email
    User findByEmail(String email);
}
