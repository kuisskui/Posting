package com.example.Posting.service;

import com.example.Posting.entity.User;
import com.example.Posting.repository.UserRepo;
import com.example.Posting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(UUID id){
        return userRepository.findById(id).orElse(new User());
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public static final int MAX_FAILED_ATTEMPTS = 3;
     
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
     
    @Autowired
    private UserRepo repo;
     
    public void increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        repo.updateFailedAttempts(newFailAttempts, user.getEmail());
    }
     
    public void resetFailedAttempts(String email) {
        repo.updateFailedAttempts(0, email);
    }
     
    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
         
        repo.save(user);
    }
     
    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();
         
        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
             
            repo.save(user);
             
            return true;
        }
         
        return false;
    }
}
