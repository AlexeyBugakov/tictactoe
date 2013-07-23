package com.tictactoe.humans;

import com.tictactoe.io.IO;

public class Player {

    private String name = "N/A";
    private int cntWon = 0;

    public Player(int i) {
        this.name = IO.getString("Имя игрока №" + (i + 1) + ": ");
    }

    public String getName() {
        return name;
    }

    public void reportWinner() {
        System.out.println("\nПобедил игрок " + name + "!");
        cntWon++;
    }

    public int getCntWon() {
        return cntWon;
    }

    @Override
    public String toString() {
        return name;
    }
}
