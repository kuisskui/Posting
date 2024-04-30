package com.example.Posting.entity;
import com.example.Posting.config.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;


    @Convert(converter = AttributeEncryptor.class)
    private String username = "Not found";

    @Convert(converter = AttributeEncryptor.class)
    private String email;
    private String password;

    @Convert(converter = AttributeEncryptor.class)
    private String firstName;

    @Convert(converter = AttributeEncryptor.class)
    private String lastName;
    private String role;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

