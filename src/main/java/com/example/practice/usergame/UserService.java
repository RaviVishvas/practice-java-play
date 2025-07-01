package com.example.practice.usergame;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> users;

    UserService(){
        users = new ArrayList<>();
    }

    public User createUser(String name, Integer userId){
        User user = new User(userId, name);

        users.add(user);

        return user;
    }
}
