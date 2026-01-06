package com.example.users.controller;

import com.example.users.model.*;
import com.example.users.data.UserService;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private final UserService repository;

    public UserController(UserService repository) {
        this.repository = repository;
    }

    @QueryMapping
    public UsersResult users() {
        try {
            List<User> users = repository.findAll();
            return new UsersPayload(users);
        } catch (Exception e) {
            return new UserError("Failed to retrieve users: " + e.getMessage());
        }
    }

    @EntityMapping
    public User user(@Argument Long userId) {
        User user = repository.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return user;
    }

    @QueryMapping
    public UserResult userQuery(@Argument Long userId) {
        try {
            User user = repository.findById(userId);
            if (user == null) {
                return new UserNotFoundError("User not found", userId);
            }
            return user;
        } catch (Exception e) {
            return new UserError("Failed to retrieve user: " + e.getMessage());
        }
    }

}
