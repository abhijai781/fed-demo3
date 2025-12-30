package com.example.users.data;

import com.example.users.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService  {

       static List<User> store = List.of(
               new User(1l, "user1", "user1@email.com"),
               new User(2l, "user2", "user2@email.com")
       );

       public  User findById(Long id) {
           System.out.println("Id coming:" + id);
           return store.stream()
                   .filter(u -> u.getUserId().equals(id))
                   .findFirst().orElse(null);
       }

    public List<User> findAll() {
           return store;
    }
}
