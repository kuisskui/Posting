package com.example.Posting.controller;

import com.example.Posting.entity.Follow;
import com.example.Posting.entity.Tag;
import com.example.Posting.repository.FollowRepository;
import com.example.Posting.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FollowController {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/searchTag")
    public List<Tag> searchTag() {
        return tagRepository.findAll();
    }

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestBody Follow follow) {
        followRepository.save(follow);
        return ResponseEntity.ok("Data received and processed successfully");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestBody Follow follow) {
        followRepository.delete(follow);
        return ResponseEntity.ok("Data received and processed successfully");
    }

    @PostMapping("/tag")
    public ResponseEntity<String> tag(@RequestBody Tag tag) {
        tagRepository.save(tag);
        return ResponseEntity.ok("Data received and processed successfully");
    }
}
