package com.example.Posting.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private String username;
    private String comment;
    private LocalDateTime createdTime;


    public CommentDTO(String username, String comment, LocalDateTime createdTime) {
        this.username = username;
        this.comment = comment;
        this.createdTime = createdTime;
    }
}
