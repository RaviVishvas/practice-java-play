package com.example.practice.lld.snakeladder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeLadderGame {

    HashMap<Integer, Integer> snakeLadderMappings;
    Queue<Player> players;

    SnakeLadderGame(){
        snakeLadderMappings = new HashMap<>();
        players = new LinkedList<>();
    }

    public void loadSnakeLadder(){
        snakeLadderMappings.put(3, 40);
        snakeLadderMappings.put(9, 60);
        snakeLadderMappings.put(30, 7);
        snakeLadderMappings.put(55, 97);
        snakeLadderMappings.put(74, 25);
    }

    public void initializePlayer(){
        Player p1 = new Player("Player1", 0);
        players.add(p1);
        Player p2 = new Player("Player2", 0);
        players.add(p2);
    }

    public void startGame(){
        boolean isAnyoneWin = false;

        while(!isAnyoneWin){
            Player p = players.poll();

            System.out.println("its turn for "+ p.name+ " from current position : "+ p.position);

            int maxValue = 6;

            int diceValue = (int)Math.random()%maxValue +1;

            System.out.println("Players roll dice value ==> "+ diceValue);

            if(p.position+diceValue==100){
                System.out.println(p.name + " wins !");
                break;
            }

            if(p.position+diceValue>100){
                System.out.println("You cant go beyond");
                players.add(p);
                continue;
            }

            int nextPos = p.position+diceValue;
            if(snakeLadderMappings.containsKey(nextPos)){
                nextPos = snakeLadderMappings.get(nextPos);
            }

            p.setPos(nextPos);
            players.add(p);
        }
    }
}
