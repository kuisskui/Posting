package com.example.Posting.dto;

import lombok.Data;

@Data
public class PostDTO {
    private String title;
    private String content;
    private String username;

    public PostDTO(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
