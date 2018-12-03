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
}
