package Hus;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayingFieldTest {

    @Test
    public void init_whenPlayingFieldInitialised_everyHoleHasTwoStonesBut8FieldsAreEmpty() {
        int result = 0;
        int expected = 48;

        PlayingField playingField = new PlayingField();
        //default constructor is calling the init-method

        for (int i = 0; i < 32; i++) {
            result += playingField.getNumberOfStones(i);
        }

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void makeMove_whenMoveMadeForHoleTwo_holeTwoIsEmpty() {
        int expected = 0;
        PlayingField playingField = new PlayingField();

        playingField.makeMove(2);
        int result = playingField.getNumberOfStones(2);

        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void makeMove_whenMoveMadeForHoleTwo_holeThreeHasThreeStones() {
        int expected = 3;
        PlayingField playingField = new PlayingField();

        playingField.makeMove(2);
        int result = playingField.getNumberOfStones(3);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void makeMove_whenMoveMadeForHoleSeven_asManyFollowingHolesShouldHaveOneStoneAsManyStonesAreInHoleSeven() {
        int expected = 2;
        PlayingField playingField = new PlayingField();

        playingField.makeMove(7);
        int result = playingField.getNumberOfStones(8) + playingField.getNumberOfStones(9) + playingField.getNumberOfStones(10);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void makeMove_whenEndOfLineReached_startWithNextLineOnSameSideOfPlayingField() {
        int expected = 3;
        PlayingField playingField = new PlayingField();

        playingField.makeMove(15);
        int result = playingField.getNumberOfStones(0);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void makeMove_whenThereIsNoStoneInTheHole_thenDontMove() {
        int expected = 2;
        PlayingField playingField = new PlayingField();

        playingField.makeMove(19);
        int result = playingField.getNumberOfStones(20);

        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void makeMove_whenFallingInHoleWithStones_keepTheMoveGoingOn() {
        int expected = 0;
        PlayingField playingField = new PlayingField();

        playingField.makeMove(5);
        int result = playingField.getNumberOfStones(7);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void stealStones_whenThereAreStonesOnTheOppositeHalf_thenStealTheseStones() {
        int expected = 4;
        PlayingField playingField = new PlayingField();

        playingField.stealStones(17);
        int result = playingField.getNumberOfStones(17);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void stealStones_whenThereAreNoStones_thenDontStealStones() {
        int expected = 2;
        PlayingField playingField = new PlayingField();

        playingField.stealStones(21);
        int result = playingField.getNumberOfStones(21);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void stealStones_whenStonesStolen_oppositeHolesAreEmpty(){
        int expected = 0;
        PlayingField playingField = new PlayingField();

        playingField.stealStones(17);
        int result = playingField.getNumberOfStones(14) + playingField.getNumberOfStones(1);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void makeMoveTest1() {
        PlayingField playingField = new PlayingField();
        // Player 1 move
        int move = 13;
        int[] expected = new int[] {
            3,3,0,3,3,0,3,3, // 0-7
            1,0,0,0,2,0,3,0, // 8-15
            0,0,0,0,2,2,2,2, // 16-23
            2,2,2,2,2,2,2,2, // 23-31
        };
        playingField.makeMove(move);
        int[] actual = playingField.getHoles();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void makeMoveTest2() {
        PlayingField playingField = new PlayingField();
        // Player 1 move (see makeMoveTest1)
        playingField.makeMove(13);
        int move = 22;
        int[] expected = new int[] {
            3,3,0,3,3,0,3,3, // 0-7
            1,0,0,0,2,0,3,0, // 8-15
            1,1,0,0,2,2,0,3, // 16-23
            0,3,3,0,3,3,0,3, // 23-31
        };
        playingField.makeMove(move);
        int[] actual = playingField.getHoles();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void makeMoveTest3() {
        PlayingField playingField = new PlayingField();
        // Player 1 move (see makeMoveTest1)
        playingField.makeMove(13);
        playingField.makeMove(22);
        int move = 4;
        int[] expected = new int[] {
            3,3,0,3,0,1,4,0, // 0-7
            2,1,1,1,2,0,3,0, // 8-15
            1,1,0,0,2,2,0,3, // 16-23
            0,3,3,0,3,3,0,3, // 23-31
        };
        playingField.makeMove(move);
        int[] actual = playingField.getHoles();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void makeMoveTest4() {
        PlayingField playingField = new PlayingField();
        // Player 1 move (see makeMoveTest1)
        playingField.makeMove(13);
        playingField.makeMove(22);
        playingField.makeMove(4);
        int move = 28;
        int[] expected = new int[] {
            3,3,0,3,0,1,4,0, // 0-7
            2,1,1,1,2,0,3,0, // 8-15
            2,2,1,1,2,2,0,3, // 16-23
            0,3,3,0,0,4,1,0, // 23-31
        };
        playingField.makeMove(move);
        int[] actual = playingField.getHoles();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void makeMoveTest5() {
        // In this test, stealing behvaviour gets tested
        PlayingField playingField = new PlayingField();
        // Player 1 move (see makeMoveTest1)
        playingField.makeMove(13);
        playingField.makeMove(22);
        playingField.makeMove(4);
        playingField.makeMove(28);
        int move = 6;
        int[] expected = new int[] {
            5,1,1,4,1,0,1,0, // 0-7
            4,0,1,3,0,2,5,2, // 8-15
            2,2,1,0,2,0,0,3, // 16-23
            0,3,0,0,0,4,1,0, // 23-31
        };
        playingField.makeMove(move);
        int[] actual = playingField.getHoles();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void makeMoveTest6() {
        // In this test, stealing behvaviour gets tested
        PlayingField playingField = new PlayingField();
        // Player 1 move (see makeMoveTest1)
        playingField.makeMove(13);
        playingField.makeMove(22);
        playingField.makeMove(4);
        playingField.makeMove(28);
        playingField.makeMove(6);
        int move = 29;
        int[] expected = new int[] {
            0,1,2,5,2,1,1,0, // 0-7
            4,0,1,4,0,2,0,0, // 8-15
            0,1,3,2,4,2,0,5, // 16-23
            0,5,0,1,1,0,0,2, // 23-31
        };
        System.out.println("Final move");
        playingField.makeMove(move);
        int[] actual = playingField.getHoles();
        assertThat(actual).isEqualTo(expected);
    }

}
