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
