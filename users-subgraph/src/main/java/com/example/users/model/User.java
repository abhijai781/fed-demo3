package com.example.users.model;


import java.util.List;

public class User {

    private Long userId;
    private String name;
    private String email;

    public User() {
    }

    public User(Long id, String name, String email) {
        this.userId = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
