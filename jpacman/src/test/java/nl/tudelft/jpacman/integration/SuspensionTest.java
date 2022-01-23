package nl.tudelft.jpacman.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Based on UserStory:
 * <p>
 * As a player,
 * I want to be able to suspend the game;
 * So  that I can pause and do something else.
 * <p>
 * Scenario S4.1: Suspend the game.
 * Given the game has started;
 * When  the player clicks the "Stop" button;
 * Then  all moves from ghosts and the player are suspended.
 * <p>
 * Scenario S4.2: Restart the game.
 * Given the game is suspended;
 * When  the player hits the "Start" button;
 * Then  the game is resumed.
 */
public class SuspensionTest {
    private final BiConsumer<Square, Square> moveAllowed = (actualSquare, moveSquare)
        -> assertThat(actualSquare).isEqualTo(moveSquare);
    private final BiConsumer<Square, Square> moveBlocked = (actualSquare, moveSquare)
        -> assertThat(actualSquare).isNotEqualTo(moveSquare);

    private Launcher launcher;
    private Game game;
    private Player player;
    private List<Ghost> ghosts;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    public void before() {
        launcher = new Launcher();
        launcher.launch();
        game = launcher.getGame();

        // gather player
        assert game.getPlayers() != null && !game.getPlayers().isEmpty() : "unable to find player in game";
        player = game.getPlayers().get(0);

        // gather nonPlayerUnits
        ghosts = new ArrayList<>();
        Board board = game.getLevel().getBoard();
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Square square = board.squareAt(x, y);
                for (Unit occupant : square.getOccupants()) {
                    if (occupant instanceof Ghost) {
                        ghosts.add((Ghost) occupant);
                    }
                }
            }
        }
        assert !ghosts.isEmpty() : "did not find any ghosts on the board!";
    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    /**
     * Exercise movement of any unit to verify its behaviour.
     *
     * @param unitToMove   unit that should try to move
     * @param moveFunction function that executes move operations of the unit
     * @param movementRule callback that will be invoked after every move with (actualSquare, squareIfMoveSuccess)
     */
    private <T extends Unit> void verifyUnitMovement(T unitToMove, BiConsumer<T, Direction> moveFunction,
                                                     BiConsumer<Square, Square> movementRule) {
        Square currentSquare = unitToMove.getSquare();
        int movesMade = 0;
        for (Direction direction : Direction.values()) {
            Square moveSquare = currentSquare.getSquareAt(direction);
            if (!moveSquare.isAccessibleTo(unitToMove) && moveSquare != currentSquare) {
                continue;
            }
            moveFunction.accept(unitToMove, direction);
            currentSquare = unitToMove.getSquare();
            movementRule.accept(currentSquare, moveSquare);
            movesMade++;
        }
        assert movesMade > 0 : "unable to test unit-move, no valid moves.";
    }

    /**
     * Exercise player movement to verify its behaviour.
     *
     * @param movementRule callback that will be invoked after every move with (actualSquare, squareIfMoveSuccess)
     */
    private void verifyPlayerMovement(BiConsumer<Square, Square> movementRule) {
        verifyUnitMovement(player, game::move, movementRule);
    }

    /**
     * Exercise ghost movement to verify its behaviour.
     *
     * @param movementRule callback that will be invoked after every move with (actualSquare, squareIfMoveSuccess)
     */
    private void verifyGhostMovement(BiConsumer<Square, Square> movementRule) {
        for (Ghost ghost : ghosts) {
            verifyUnitMovement(ghost, game.getLevel()::move, movementRule);
        }
    }

    /**
     * Scenario S4.1 assumes that units can move when the game is not stopped.
     * Verify that this is the case.
     */
    @Test
    public void test_whenGameStarted_thenUnitsMove() {
        game.start();
        verifyPlayerMovement(moveAllowed);
        verifyGhostMovement(moveAllowed);
    }

    /**
     * Verify that Scenario S4.1 holds true.
     */
    @Test
    public void test_whenGameSuspended_thenNoUnitsMove() {
        // Note: I would have preferred to use the Start and Stop JButtons directly,
        //   but stringing my way through private fields strikes me as bad practice, so this will have to do
        game.start();
        game.stop();
        verifyPlayerMovement(moveBlocked);
        verifyGhostMovement(moveBlocked);
    }

    /**
     * Verify that Scenario S4.2 holds true.
     */
    @Test
    public void test_whenGameResumed_thenUnitsMove() {
        game.start();
        game.stop();
        game.start();
        verifyPlayerMovement(moveAllowed);
        verifyGhostMovement(moveAllowed);
    }
}
