package com.example.Posting.dto;

import lombok.Data;

@Data
public class PostDTO {
    private Integer id;
    private String title;
    private String content;
    private String username;

    public PostDTO(Integer id, String title, String content, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
