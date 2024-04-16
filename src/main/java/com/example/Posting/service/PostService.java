package com.example.Posting.service;

import com.example.Posting.entity.Post;
import com.example.Posting.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public void createPost(Post post){
        postRepository.save(post);
    }
}
