import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Gui class
 * Display the game in a JFrame.
 * Has to be called back after every move
 * in order to update the frame
 */
public class Gui {

  /*
    Constants
   */

  /**
   * Width and height of the Gui JFrame
   */
  private static final int WINDOW_WIDTH = 480;
  private static final int WINDOW_HEIGHT = 320;
  private static final String WINDOW_TITLE = "Hus";

  /*
    Attributes
   */

  /**
   * The JFrame which is used to visualize the game and
   * holds every Gui component
   */
  private JFrame frame;

  /**
   * The JPanel applied to the JFrame.
   * Manages the Layout
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
  public Gui(int numHoles) {
    // TODO maybe add a text field above the buttons
    //      for messages/information like whose turn it is, game time, etc.
    this.frame = new JFrame();
    this.panel = new JPanel(new GridLayout(4,8));
    this.buttons = new JButton[numHoles];
    init();
  }

  /*
    Private Methods
   */

  /**
   * Initializes the Gui and adds all needed components to it
   * including PlayingField Holes and MouseListeners
   */
  private void init() {
    // Apply defined constants
    frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    frame.setTitle(WINDOW_TITLE);
    // Add every button to the panel
    for(int i = 0; i < buttons.length; i++) {
      buttons[i] = new JButton("0");
      buttons[i].setBackground(new java.awt.Color(193, 141, 56));
      buttons[i].setOpaque(true);
      buttons[i].setBorderPainted(true);
      buttons[i].addMouseListener(new GuiMouseListener(this,i));
      panel.add(buttons[i]);
    }
    frame.add(panel);
    // This centers the JFrame
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /*
    Public Methods
   */

  /**
   * Updates the JFrame with all of its components
   * @param playingField the playingField which has to be displayed
   */
  public void update(PlayingField playingField) {
    for(int i = 0; i < buttons.length; i++) {
      buttons[i].setText(String.valueOf(playingField.getNumberOfStones(0)));
    }
    // TODO maybe gray out the enemies buttons, so the player can only click his own buttons
  }

  /**
   * This method gets called by GuiMouseListener when a button gets clicked with the mouse
   * @param id the button which has been clicked ([0;31])
   */
  public void buttonCallback(int id) {
    // TODO remove the following output when method is fully implemented
    System.out.println("Button " + id + " has been clicked.");
    // TODO call makeMove here! (note that ID is between 0 and 31, so look whose player's board it is
  }

}
