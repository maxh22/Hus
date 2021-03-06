package Gui;

import Hus.Main;
import Hus.PlayingField;

import javax.swing.*;
import java.awt.*;

/**
 * Hus.Gui class Display the game in a JFrame. Has to be called back after every move in order to
 * update the frame
 */
public class Gui {

  /*
    Constants
   */

    /**
     * Width and height of the Hus.Gui JFrame
     */
    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGHT = 320;
    private static final String WINDOW_TITLE = "Hus";
    private final int numberOfHoles = 32;
    private int lastClickedButton;

    /**
     * Specifies if the game has already finished and thus should not be modifiable anymore
     */
    private boolean finished;
    /**
     * The JFrame which is used to visualize the game and holds every Hus.Gui component
     */
    private JFrame frame;

    /**
     * The JPanel applied to the JFrame. Manages the Layout
     */
    private JPanel panel;

    /**
     * The buttons which represent the holes
     */
    private JButton[] buttons;


  /*
    Constructors(s)
   */


    /**
     * Creates a GUI instance and calls init()
     */
    public Gui() {
        // TODO maybe add a text field above the buttons
        //      for messages/information like whose turn it is, game time, etc.
        this.finished = false;
        this.frame = new JFrame();
        this.panel = new JPanel(new GridLayout(4, 8));
        this.buttons = new JButton[numberOfHoles];
        init();
    }

    /*
        Private Methods
      */

    /**
     * Initializes the Hus.Gui and adds all needed components to it including Hus.PlayingField Holes
     * and MouseListeners
     */

    private void init() {
        // Apply defined constants
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setTitle(WINDOW_TITLE);
        // Add every button to the panel
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("0");
            buttons[i].setBackground(new Color(193, 141, 56));
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(true);
            buttons[i].addMouseListener(new GuiMouseListener(this, i));
        }
        panelCreator();
        frame.add(panel);
        // This centers the JFrame
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


  /*
    Public Methods
   */

    private void panelCreator() {
        for (int i = 0; i <= 7; i++) {
            panel.add(buttons[i]);
        }

        for (int i = 15; i >= 8; i--) {
            panel.add(buttons[i]);
        }

        for (int i = 16; i <= 23; i++) {
            buttons[i].setBackground(new Color(153, 102, 0));
            panel.add(buttons[i]);
        }

        for (int i = 31; i >= 24; i--) {
            buttons[i].setBackground(new Color(153, 102, 0));
            panel.add(buttons[i]);
        }

    }

    /**
     * panel.add(buttons[i]); Updates the JFrame with all of its components
     *
     * @param playingField the playingField which has to be displayed
     */
    public void update(PlayingField playingField) {
        if (finished)
            return;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(String.valueOf(playingField.getNumberOfStones(i)));
        }
    }

    /**
     * Called when one player has one, so no further moves are possible
     */
    public void gameFinished() {
        finished = true;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
            for (int j = 0; j < buttons[i].getMouseListeners().length; j++) {
                buttons[i].removeMouseListener(buttons[i].getMouseListeners()[j]);
            }
        }
    }

    /**
     * This method gets called by Hus.GuiMouseListener when a button gets clicked with the mouse
     *
     * @param id the button which has been clicked ([0;31])
     */
    public void buttonCallback(int id) {
        Main.makeMove(id);
    }
}
