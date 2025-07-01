package com.example.practice.usergame;



import java.util.Objects;

public class MainGame {


    public static void main(String[] args) {

        UserService userService = new UserService();

        UserGroupService userGroupService = new UserGroupService();

        User user = userService.createUser("user1", 1);
        System.out.print("user is created : " +user.userId+"  "+ user.userName);

        UserGroup group = userGroupService.creatUserGroup(1, "group1");

        System.out.println("User group is created :" + group.groupId + "  "+group.groupName);

        UserGroup group1 = userGroupService.join(group.groupId, user);
        System.out.println( "group" + group1);

        UserGroup retreivedGroup = userGroupService.getGroups(1);
        if(Objects.nonNull(retreivedGroup))
        System.out.println(" get an group : " + retreivedGroup);
    }

}
