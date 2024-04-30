package com.example.Posting.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID userId;
    private String title;
    private String content;
    private LocalDateTime createdTime;

    public Post(){}

    public Post(UUID userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @PrePersist
    protected void onCreate() {
        createdTime = LocalDateTime.now();
    }

}
