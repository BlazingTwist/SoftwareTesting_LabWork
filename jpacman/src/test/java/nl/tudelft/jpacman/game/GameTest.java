package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 * A test for all branches from the Game.start() method.
 */
public class GameTest {

    private Level levelMock = null;
    private SinglePlayerGame singlePlayerGame = null;

    /**
     * Called before every Test.
     */
    @BeforeEach
    public void setUp() {
        levelMock = Mockito.mock(Level.class);
        singlePlayerGame = Mockito.spy(new SinglePlayerGame(
            Mockito.mock(Player.class),
            levelMock,
            Mockito.mock(PointCalculator.class)
        ));
    }

    /**
     * A Test that exercises all branches of Game::start();
     */
    @ParameterizedTest
    @CsvSource({
        "true, false, false, 0", // Level should not be started again if a game is already in progress.
        "false, true, false, 0", // Level should not be started when there are no players.
        "false, false, true, 0", // Level should not be started when there are no pellets.
        "false, false, false, 1", // Verify that start method actually starts the Level otherwise.
    })
    public void test_start_branchCoverage(boolean gameAlreadyInProgress, boolean noLivingPlayers, boolean noPellets,
                                          int expectedTimesLevelStarted) {
        when(singlePlayerGame.isInProgress()).thenReturn(gameAlreadyInProgress);
        when(levelMock.isAnyPlayerAlive()).thenReturn(!noLivingPlayers);
        when(levelMock.remainingPellets()).thenReturn(noPellets ? 0 : 1);

        singlePlayerGame.start();

        Mockito.verify(levelMock, Mockito.times(expectedTimesLevelStarted)).start();
    }
}
