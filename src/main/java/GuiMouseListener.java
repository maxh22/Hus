import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiMouseListener implements MouseListener {

  /**
   * The button ID ([0;31])
   */
  private int id;

  /**
   * The Gui, which the callback is sent to (via Gui.buttonCallback(int id))
   */
  private Gui gui;

  /**
   * The constructor of a MouseListener. Gets called by every button
   *
   * @param gui see gui attribute
   * @param id see id attribute
   */
  public GuiMouseListener(Gui gui, int id) {
    this.id = id;
    this.gui = gui;
  }

  /**
   * Called automatically when the mouse clicks on a button. Invokes gui.buttonCallback(id)
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    gui.buttonCallback(id);
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
