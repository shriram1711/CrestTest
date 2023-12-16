package com.test.Chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Chat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    Optional<User> findById(Long id);
}
