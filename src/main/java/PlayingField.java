public class PlayingField {

  int[] holes = new int[32];

  public PlayingField() {
    for (int i = 0; i <= 7; i++) {
      holes[i] = 2;
    }

    for (int i = 12; i <= 15; i++) {
      holes[i] = 2;
    }

    for (int i = 20; i <= 31; i++) {
      holes[i] = 2;
    }
  }

  public void makeMove(int startingHole) {
    boolean moveFinished = false;
    while (!moveFinished) {
      if (holes[startingHole] > 1) {
        int stonesInStartingHole = holes[startingHole];
        holes[startingHole] = 0;
        int landingHole = startingHole + stonesInStartingHole;

        if (landingHole > 15 && startingHole < 15) {
          landingHole = landingHole - 16;

        } else if (landingHole > 31 && startingHole > 15) {
          landingHole = landingHole - 16;
        }

        for (int i = startingHole + 1; i <= landingHole; i++) {
          if (i == 15) {
            holes[i]++;
            i = 0;
          } else if (i == 31) {
            holes[i]++;
            i = 16;
          } else {
            holes[i]++;
          }
        }
        startingHole = landingHole;
      } else {
        moveFinished = true;
      }
    }
  }

  public int getNumberOfStones(int hole) {
    return holes[hole];
  }

}
