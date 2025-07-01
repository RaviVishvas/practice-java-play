package com.example.practice.usergame;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {

    public Integer groupId;
    public String groupName;

    List<User> users = new ArrayList<>();

    UserGroup(Integer id, String groupName){
        this.groupId = id;
        this.groupName = groupName;
    }

}
