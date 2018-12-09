package Hus;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * These test cases are bases on this video:
 * https://www.youtube.com/watch?v=_DwyRYPy8sU
 */
public class PlayingFieldVideoMovesTest {

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
    int[] actual = playingField.getStonesInHole();
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
    int[] actual = playingField.getStonesInHole();
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
    int[] actual = playingField.getStonesInHole();
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
    int[] actual = playingField.getStonesInHole();
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
    int[] actual = playingField.getStonesInHole();
    assertThat(actual).isEqualTo(expected);
  }


}
