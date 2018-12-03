package Hus;

import Helper.HoleMappingHelper;

public class PlayingField {

    private final int numberOfHoles = 32;

    private int[] stonesInHole = new int[numberOfHoles];

    public PlayingField() {
        init();
    }

    private void init() {
        for (int i = 0; i <= 7; i++) {
            stonesInHole[i] = 2;
        }

        for (int i = 12; i <= 15; i++) {
            stonesInHole[i] = 2;
        }

        for (int i = 20; i <= 31; i++) {
            stonesInHole[i] = 2;
        }
    }

    public void makeMove(int startingHole) {
        while (stonesInHole[startingHole] > 1) {
            int landingHole = startingHole + stonesInHole[startingHole];
            for (int i = startingHole + 1; i <= landingHole; i++) {
                if (endOfLineReached(i)) {
                    i -= 16;
                    landingHole -= 16;
                }
                stonesInHole[i]++;
            }
            stonesInHole[startingHole] = 0;
            if (isInFrontLine(landingHole) && stonesInHole[landingHole] > 1) {
                stealStones(landingHole);
            }
            startingHole = landingHole;
        }
    }

    private boolean endOfLineReached(int holeID) {
        if (holeID == 16 || holeID == 32) {
            return true;
        }
        return false;
    }

    private boolean isInFrontLine(int holeID) {
        if (holeID > 7 && 24 > holeID) {
            return true;
        }
        return false;
    }

    public int getNumberOfStones(int holeID) {
        return stonesInHole[holeID];
    }

    public void stealStones(int holeID) {
        int[] holesOnTheOpposite;
        holesOnTheOpposite = HoleMappingHelper.getIdOfOppositeHole(holeID);
        int stonesInBothOppositeHoles = stonesInHole[holesOnTheOpposite[0]] + stonesInHole[holesOnTheOpposite[1]];
        if (stonesInHole[holesOnTheOpposite[0]] > 0) {
            stonesInHole[holeID] += stonesInBothOppositeHoles;
            stonesInHole[holesOnTheOpposite[0]] = 0;
            stonesInHole[holesOnTheOpposite[1]] = 0;
        }
    }
}
