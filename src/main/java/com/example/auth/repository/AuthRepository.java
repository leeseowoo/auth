package com.example.auth.repository;

import com.example.auth.service.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
