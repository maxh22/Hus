public class Match {

    private PlayingField playingField;

    public void initMatch() {
        playingField = new PlayingField();
    }

    public void makeMove(int hole) {
        if (!isGameFinished()) {
            playingField.makeMove(hole);
        }
    }

    public boolean isGameFinished() {

        for (int i = 0; i < 32; i++)
        {
            if (playingField.getNumberOfStones(i) > 1) {
                return false;
            }
        }
        return true;
    }
}
