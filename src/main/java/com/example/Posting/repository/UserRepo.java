package com.example.Posting.repository;

import org.springframework.data.jpa.repository.*;

import com.example.Posting.entity.User;
 
public interface UserRepo extends JpaRepository<User, Integer> {
         
    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String email);
     
}