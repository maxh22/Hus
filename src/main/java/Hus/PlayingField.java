package Hus;

import Helper.HoleMappingHelper;

public class PlayingField {

    private final int numberOfHoles = 32;

    private int[] stonesInHole = new int[numberOfHoles];

    private int startingHole;
    private int landingHole;

    PlayingField() {
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

    void makeMove(int holeToStartFrom) {
        startingHole = holeToStartFrom;

        while (stonesInHole[startingHole] > 1) {

            landingHole = startingHole + stonesInHole[startingHole];

            moveStones();

            stonesInHole[startingHole] = 0;

            if (isHoleInFrontLine(landingHole) && stonesInHole[landingHole] > 1) {
                stealStones(landingHole);
            }

            startingHole = landingHole;
        }
    }

    private void moveStones() {
        for (int i = startingHole + 1; i <= landingHole; i++) {
            if (endOfLineReached(i)) {
                i -= 16;
                landingHole -= 16;
            }
            stonesInHole[i]++;
        }
    }

    private boolean endOfLineReached(int holeID) {
        return holeID == 16 || holeID == 32;
    }

    private boolean isHoleInFrontLine(int holeID) {
        return holeID > 7 && 24 > holeID;
    }

    public int getNumberOfStones(int holeID) {
        return stonesInHole[holeID];
    }

    void stealStones(int holeID) {
        int[] holesOnTheOpposite;

        holesOnTheOpposite = HoleMappingHelper.getIdOfOppositeHole(holeID);

        int stonesInBothOppositeHoles = stonesInHole[holesOnTheOpposite[0]] + stonesInHole[holesOnTheOpposite[1]];

        if (stonesInHole[holesOnTheOpposite[0]] > 0) {

            stonesInHole[holeID] += stonesInBothOppositeHoles;
            stonesInHole[holesOnTheOpposite[0]] = 0;
            stonesInHole[holesOnTheOpposite[1]] = 0;
        }
    }

    int[] getStonesInHole() {
        return stonesInHole;
    }
}
