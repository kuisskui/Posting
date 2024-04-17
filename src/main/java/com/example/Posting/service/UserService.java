package com.example.Posting.service;

import com.example.Posting.entity.User;
import com.example.Posting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Integer id){
        return userRepository.findById(id).orElse(new User());
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
