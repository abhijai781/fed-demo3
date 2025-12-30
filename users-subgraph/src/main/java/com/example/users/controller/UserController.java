package com.example.users.controller;

import com.example.users.model.User;
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
    public List<User> users() {
        return repository.findAll();
    }



    @EntityMapping
    @QueryMapping
    public User user(@Argument Long userId) {
        return repository.findById(userId);
    }

}
