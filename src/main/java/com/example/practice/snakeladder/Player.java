package com.example.practice.snakeladder;

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
