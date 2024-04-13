package com.example.Posting.service;

import com.example.Posting.dto.SignupRequest;
import com.example.Posting.entity.User;
import com.example.Posting.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username) == null;
    }

    public void createUser(SignupRequest dto) {
        User dao = modelMapper.map(dto, User.class);

        String hashedPassword = passwordEncoder.encode(dao.getPassword());

        dao.setPassword(hashedPassword);

        userRepository.save(dao);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
