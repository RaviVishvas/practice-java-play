package com.example.practice.snakeladder;

public class MainGame {

    public static void main(String[] args) {
        SnakeLadderGame game = new SnakeLadderGame();

        game.loadSnakeLadder();
        game.initializePlayer();

        game.startGame();
    }
}
