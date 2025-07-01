package com.example.practice.coding;

import com.example.practice.usergame.User;

import java.util.HashSet;
import java.util.Set;

public class HashMapChallenge {




    public static void main(String[] args) {

        Set<User> set = new HashSet<>();

        set.add(new User("lol"));
        set.add(new User("lol"));

        System.out.println("size===>" + set.size());

    }
}
