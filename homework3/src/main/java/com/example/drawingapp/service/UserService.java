package com.example.drawingapp.service;

import com.example.drawingapp.entity.User;
import com.example.drawingapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name) {
        if (userRepository.existsByName(name)) {
            throw new RuntimeException("User with name '" + name + "' already exists");
        }
        
        User user = new User(name);
        return userRepository.save(user);
    }
    
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User with name '" + name + "' not found"));
    }
    
    public boolean userExists(String name) {
        return userRepository.existsByName(name);
    }
} 