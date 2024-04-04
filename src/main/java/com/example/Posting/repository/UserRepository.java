package com.example.Posting.repository;

import com.example.Posting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // SELECT * FROM Member WHERE username = ‘username in parameter’
    User findByUsername(String username);
}
