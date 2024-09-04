package com.example.esspi.service;

import org.springframework.stereotype.Service;

import com.example.esspi.model.User;
import com.example.esspi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(String username, String password) {
        userRepository.createUser(username, password);
    }
}
