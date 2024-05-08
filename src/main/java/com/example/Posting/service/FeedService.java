package com.example.Posting.service;

import com.example.Posting.dto.CommentDTO;
import com.example.Posting.dto.PostDTO;
import com.example.Posting.entity.Comment;
import com.example.Posting.entity.Post;
import com.example.Posting.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    public List<PostDTO> findPostDTOs(){

        List<PostDTO> postDTOs = new ArrayList<>();
        List<Post> posts = postService.findAll();
        for (Post post: posts){
            User user = userService.findById(post.getUserId());
            postDTOs.add(new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getUsername()));
        }
        return postDTOs;
    }

    public PostDTO findPostDTOิById(Integer id){
        Post post = postService.findById(id);
        User user = userService.findById(post.getUserId());
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), user.getUsername());
    }

    public List<CommentDTO> findCommentDTOsByPostId(Integer postId) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        List<Comment> comments = commentService.findByPostId(postId);

        for (Comment comment: comments){
            User user = userService.findById(comment.getUserId());
            commentDTOs.add(new CommentDTO(user.getUsername(), comment.getComment(), comment.getCreatedTime()));
        }
        return commentDTOs;
    }
}
