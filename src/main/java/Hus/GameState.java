package Hus;

/**
 * Represents the current state of the game
 * (either running or one of the players won the game)
 */
public enum GameState {

  RUNNING /* The game is still running, no one won yet */ ,
  PLAYER1_WON /* The player on the upper side won */ ,
  PLAYER2_WON /* The player on the lower side won */ ,

}
