package com.example.practice.usergame;

import java.util.ArrayList;
import java.util.List;

public class UserGroupService {

    List<UserGroup> groups;

    UserGroupService(){
        groups = new ArrayList<>();
    }

    public UserGroup creatUserGroup(Integer id, String groupName){
        UserGroup group = new UserGroup(id, groupName);

        groups.add(group);
        return group;
    }

    public UserGroup join(Integer id, User user){
        UserGroup group = groups.get(id);

        group.users.add(user);

        return group;
    }

    public UserGroup getGroups(Integer id){

        for(var group : groups){
            if(group.groupId == id) return group;
        }

        return null;
    }
}
