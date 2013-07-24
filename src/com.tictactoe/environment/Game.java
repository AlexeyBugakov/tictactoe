package com.tictactoe.environment;

import com.tictactoe.humans.Player;

public class Game {

    private static final int CNT_PLAYERS = 2;
    private static int step;

    private Player[] players = new Player[CNT_PLAYERS];
    private static Field field;

    public Player[] getPlayers() {
        return players;
    }

    public Game() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i);
        }
        field = new Field();
        step = 1;
    }

    public Game(Player[] players) {
        this.players = players;
        field = new Field();
        step = 1;
    }

    public void playGame() {
        int winnerPlayer;
        field.showField();
        while (true) {
            winnerPlayer = field.doStep(step++);
            if (winnerPlayer != -1) {
                if (winnerPlayer == -2) {
                    System.out.println("Ничья!");
                } else {
                    players[winnerPlayer].reportWinner();
                }
                break;
            }
        }
    }

    public void endGame() {
        System.out.println("\nСпасибо за игру!\nСтатистика: ");
        for (int i = 0; i < players.length; i++) {
            System.out.println("Игрок №" + (i + 1) + " (" + players[i].getName() + ") количество побед = " + players[i].getCntWon());
        }
    }

}
