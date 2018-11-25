import org.junit.Assert;
import org.junit.Test;

public class PlayingFieldTest {

  @Test
  public void makeMove_whenNoStonesInField_thenDontMove() {
    PlayingField playingField = new PlayingField();

    playingField.makeMove(10);
    Assert.assertEquals(0, playingField.getNumberOfStones(10));
  }

  @Test
  public void constructor_whenGameIsInitialized_thenStonesAreSetCorrect() {
    PlayingField playingField = new PlayingField();
    int result = 0;
    for (int i = 0; i <= 31; i++) {
      result = result + playingField.getNumberOfStones(i);
    }
    Assert.assertEquals(48, result);
  }

  @Test
  public void makeMove_whenMoveIsMade_thenStonesInStartingHoleAreGone() {
    PlayingField playingField = new PlayingField();

    playingField.makeMove(2);

    Assert.assertEquals(0, playingField.getNumberOfStones(2));
  }

  @Test
  public void makeMove_whenHole15IsMoved_thenMoveToHole0() {
    PlayingField playingField = new PlayingField();
    playingField.makeMove(13);

    Assert.assertEquals(3, playingField.getNumberOfStones(0));
  }
}
