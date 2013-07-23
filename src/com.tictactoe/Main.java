package com.tictactoe;

import com.tictactoe.environment.Game;
import com.tictactoe.io.IO;

public class Main {

    public static void main(String[] args) {
        System.out.println("q");
        Game game = new Game();
        while (true) {
            game.playGame();
            if (IO.getString("\nНачать новую игру? ").compareToIgnoreCase("y") == 0) {
                game = new Game(game.getPlayers());
            } else {
                game.endGame();
                break;
            }
        }
    }
}
