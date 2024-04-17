package com.example.Posting.service;

import com.example.Posting.dto.CommentRequest;
import com.example.Posting.entity.Comment;
import com.example.Posting.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    public void save(CommentRequest commentRequest, Integer userId, Integer postId){
        Comment comment = modelMapper.map(commentRequest, Comment.class);
        comment.setUserId(userId);
        comment.setPostId(postId);
        commentRepository.save(comment);
    }

    public List<Comment> findByPostId(Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
