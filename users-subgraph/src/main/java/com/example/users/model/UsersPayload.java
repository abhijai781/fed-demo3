package com.example.users.model;

import java.util.List;

public class UsersPayload implements UsersResult {
    private List<User> users;

    public UsersPayload(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
