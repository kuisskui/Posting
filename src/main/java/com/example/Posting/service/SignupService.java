package com.example.Posting.service;

import com.example.Posting.entity.User;
import com.example.Posting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username) == null;
    }

    public void createUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
