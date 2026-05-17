package com.example.practice.splitwise.service;

import com.example.practice.splitwise.model.User;
import com.example.practice.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(String name, String email, String phoneNumber) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserRegistrationException("User with email " + email + " already exists.");
        }
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new UserRegistrationException("User with phone number " + phoneNumber + " already exists.");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found."));
    }
}

class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String message) {
        super(message);
    }
}
