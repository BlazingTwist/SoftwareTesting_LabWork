package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * A test for all branches from the Game.start() method.
 */
public class GameUnitTest {

   Player playerMock = Mockito.mock(Player.class);
   Level levelMock = Mockito.mock(Level.class);
   PointCalculator pcMock = Mockito.mock(PointCalculator.class);
   SinglePlayerGame game = Mockito.spy(new SinglePlayerGame(playerMock, levelMock, pcMock));

    /**
     *  Verifiers that the game is currently running.
     */
   @Test
   public void test_gameInProgress(){
        when(game.isInProgress()).thenReturn(true);

        game.start();

        Mockito.verify(levelMock, Mockito.times(0)).start();
    }

    /**
     * Verifies that the game is not yet running and that it will be initialized.
     */
    @Test
    public void test_createNewGame(){
        when(game.isInProgress()).thenReturn(false);
        when(levelMock.isAnyPlayerAlive()).thenReturn(true);
        when(levelMock.remainingPellets()).thenReturn(1);

        game.start();

        Mockito.verify(levelMock, Mockito.times(1)).start();
    }

    /**
     * Verifies that the game is not yet running and that one parameter for the initialization is missing.     */
    @Test
    public void test_gameOver(){
        when(game.isInProgress()).thenReturn(false);
        when(levelMock.isAnyPlayerAlive()).thenReturn(false);

        game.start();

        Mockito.verify(levelMock, Mockito.times(0)).start();
    }
}
