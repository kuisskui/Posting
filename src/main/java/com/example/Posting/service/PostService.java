package com.example.Posting.service;

import com.example.Posting.entity.Post;
import com.example.Posting.repository.PostRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(Integer id){
        return postRepository.findById(id).orElse(new Post());
    }
}
