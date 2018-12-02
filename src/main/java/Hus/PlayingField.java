package Hus;

public class PlayingField {


    private int[] holes = new int[32];
    private GameState gameState;

    public PlayingField() {
        init();
    }

    private void init() {
        this.gameState = GameState.RUNNING;
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

    /**
     * Returns the opposing holes IDs (the one the current player can steal from)
     * @param id the current hole ID
     * @return an int array {nearest opposing hole; second-nearest opposing hole}
     */
    public int[] getOpposingHoleIds(int id) {
        if(id >= 8 && id <= 15) {
            return new int[] {id+8, id+16};
        } else if (id >= 16 && id <= 23) {
            return new int[] {id-8, id-16};
        } else {
            return null;
        }
    }

    /**
     * Returns the next stone (clock-wise)
     * Help method for makeMove()
     * @param id the hole ID
     */
    private int getFollowingHoleId(int id) {
        if(id <= 15) {
            return (id+1) % 16;
        } else {
            return ((id-16+1) % 16) + 16;
        }
    }

    /**
     * Executes a move and recalculates the PlayingField.
     * Also updates the gameState
     * @param startingHole
     * @return true if success, false if move was invalid
     */
    public boolean makeMove(int startingHole) {
        if(getNumberOfStones(startingHole) < 2) {
            return false;
        }

        int currentId = startingHole;
        int remainingStones = getNumberOfStones(currentId);
        setNumberOfStones(currentId, 0);
        while(remainingStones > 0) {
            currentId = getFollowingHoleId(currentId);
            setNumberOfStones(currentId, getNumberOfStones(currentId) + 1);
            remainingStones--;
            if(remainingStones == 0 && getNumberOfStones(currentId) > 1) {
                remainingStones = getNumberOfStones(currentId);
                setNumberOfStones(currentId, 0);
                // Now look if the player can steal stones using help method getOpposingHoleIds()
                int[] opposingHoles = getOpposingHoleIds(currentId);
                if(opposingHoles != null) {
                    if(getNumberOfStones(opposingHoles[0]) > 0) {
                        remainingStones += getNumberOfStones(opposingHoles[0]);
                        remainingStones += getNumberOfStones(opposingHoles[1]);
                        int stolen = getNumberOfStones(opposingHoles[0]) + getNumberOfStones(opposingHoles[1]);
                        setNumberOfStones(opposingHoles[0], 0);
                        setNumberOfStones(opposingHoles[1], 0);
                        System.out.println(currentId + " stole " + stolen + " stones from " + opposingHoles[0] + " and " + opposingHoles[1]);
                    }
                }
            }
        }
        updateGameState();

        return true;
    }

    /**
     * Determines the current game state and stores it into this.gameState
     */
    private void updateGameState() {
        boolean player1lost = true;
        boolean player2lost = true;
        for (int i = 0; i < 16; i++) {
            if (getNumberOfStones(i) > 1) {
                player1lost = false;
                break;
            }
        }
        for (int i = 16; i < 32; i++) {
            if (getNumberOfStones(i) > 1) {
                player2lost = false;
                break;
            }
        }
        if(player1lost) {
            this.gameState = GameState.PLAYER2_WON;
            Main.gameFinished();
        } else if (player2lost) {
            this.gameState = GameState.PLAYER1_WON;
            Main.gameFinished();
        } else {
            this.gameState = GameState.RUNNING;
        }
    }

    /**
     * Returns the current gameState to determine if the game is over (see GameState.java)
     * @return
     */
    public GameState getGameState() {
        return gameState;
    }

    public void makeMoveOld(int startingHole) {
        System.out.println("makeMove Start");
        boolean moveFinished = false;
        if (!isGameFinishedOld()) {
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
        System.out.println("makeMove End");
    }

    private boolean isGameFinishedOld() {
        for (int i = 0; i < 32; i++) {
            if (holes[i] > 1) {
                return false;
            }
        }
        return true;
    }

    public int getNumberOfStones(int holeID) {
        return holes[holeID];
    }
    public void setNumberOfStones(int holeID, int value) {
        this.holes[holeID] = value;
    }
}
