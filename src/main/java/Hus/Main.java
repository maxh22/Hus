package Hus;

import Gui.Gui;

public class Main {

    public static void main(String[] args) {
        Gui gui = new Gui();
        PlayingField playingField =  new PlayingField();
        gui.update(playingField);
    }
}
