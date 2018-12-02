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
        playingField.makeMove(id);
        gui.update(playingField);
    }

}
