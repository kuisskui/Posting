package com.example.Posting.service;

import com.example.Posting.dto.PostDTO;
import com.example.Posting.entity.Post;
import com.example.Posting.entity.User;
import com.example.Posting.repository.PostRepository;
import com.example.Posting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public List<PostDTO> findPostDTOs(){

        List<PostDTO> postDTOS = new ArrayList<>();
        List<Post> posts = postService.findAll();
        for (Post post: posts){
            User user = userService.findById(post.getUserId());
            postDTOS.add(new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getUsername()));
        }
        return postDTOS;
    }

    public PostDTO findPostDTOิById(Integer id){
        Post post = postService.findById(id);
        User user = userService.findById(post.getUserId());
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getUsername());
    }
}
