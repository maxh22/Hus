public class Match {

  private Gui gui;
  private PlayingField playingField;
  private final int numberOfHoles = 32;

  public Match() {
    // TODO maybe extract this 32 and make it a constant or sth
    //      (also in other classes)
    this.gui = new Gui(numberOfHoles);
  }

  public void initMatch() {
    playingField = new PlayingField();
    gui.update(playingField);
  }

  public void makeMove(int hole) {
    if (!isGameFinished()) {
      playingField.makeMove(hole);
    }

    // TODO does this belong here?
    gui.update(playingField);
  }

  public boolean isGameFinished() {
    for (int i = 0; i < numberOfHoles; i++) {
      if (playingField.getNumberOfStones(i) > 1) {
        return false;
      }
    }
    return true;
  }
}
