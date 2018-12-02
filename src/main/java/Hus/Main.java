package Hus;

import Gui.Gui;

public class Main {

    public static PlayingField playingField;
    public static Gui gui;

    public static void main(String[] args) {
        gui = new Gui();
        playingField = new PlayingField();
        gui.update(playingField);
    }

    public static void buttonClicked(int id) {
        if(!playingField.makeMove(id)) {
            System.out.println("Invalid move! There must be at least 2 stones in the chosen hole.");
            // TODO maybe show warning in a textfield or something
        }
        gui.update(playingField);
        if(playingField.getGameState() == GameState.PLAYER1_WON) {
            System.out.println("Player 1 won!");
            gameFinished();
        } else if(playingField.getGameState() == GameState.PLAYER2_WON) {
            System.out.println("Player 2 won!");
            gameFinished();
        }
    }

    public static void gameFinished() {
        gui.update(playingField);
        gui.gameFinished();
    }
}
