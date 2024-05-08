package com.example.Posting.service;

import com.example.Posting.entity.User;
import com.example.Posting.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private static final int MAX_FAILED_ATTEMPTS = 3;

    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        user.setAccountNonLocked(true);
        if (!user.isAccountNonLocked()) {
            throw new LockedException("User account is locked");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        logger.info(username + " has successfully logged in at "
                + Instant.now());
        logger.info(username + " signing in with role " + user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    // public void updateFailedAttempts(User user) {
    //     user.setFailedAttempt(user.getFailedAttempt() + 1);
    //     if (user.getFailedAttempt() >= MAX_FAILED_ATTEMPTS) {
    //         user.setAccountNonLocked(false);
    //         user.setLockTime(new Date());
    //     }
    //     userRepository.save(user);
    // }

    public void updateFailedAttempts(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setFailedAttempt(user.getFailedAttempt() + 1);
            if (user.getFailedAttempt() >= MAX_FAILED_ATTEMPTS) {
                user.setAccountNonLocked(false);
                user.setLockTime(new Date());
            }
            userRepository.save(user);
        }
    }
    

    public void saveUserDetails(String username, String email, String firstName, String lastName){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }
}

