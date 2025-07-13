package com.example.practice.lld.snakeladder;

public class Player {

    String name;
    int position;

    Player(String name, int position){
        this.name = name;
        this.position= position;
    }

    public void setPos(int position){
        this.position = position;
    }
}
