package nl.tudelft.jpacman.integration;

import java.util.EnumMap;
import java.util.Map;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GhostMapParser;

/**
 * Exercise Scenarios 2.1, 2.2 and 2.3.
 */
public class PlayerMovementTest {
    // since Direction does not contain an "opposite" method
    //  (which in my opinion it really should, instead of storing it in the Clyde class)
    // we have to manually store opposites here.
    private Map<Direction, Direction> oppositeDirection = new EnumMap<>(Direction.class);

    private Launcher launcher;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    public void before() {
        launcher = new Launcher();
        oppositeDirection.put(Direction.NORTH, Direction.SOUTH);
        oppositeDirection.put(Direction.EAST, Direction.WEST);
        oppositeDirection.put(Direction.SOUTH, Direction.NORTH);
        oppositeDirection.put(Direction.WEST, Direction.EAST);
    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    private Game startGameWithMap(String mapFile) {
        launcher.withMapFile(mapFile);
        launcher.launch();
        Game game = launcher.getGame();
        game.start();
        assert game.isInProgress() : "Game was not in progress!";
        return game;
    }

    private static Player getPlayer(Game game) {
        assert game.getPlayers() != null && !game.getPlayers().isEmpty() : "unable to find player in game";
        return game.getPlayers().get(0);
    }

    /**
     * Verify that Scenario S2.1 holds true.
     *
     * @throws ClassNotFoundException should not be thrown.
     */
    @Test
    public void test_playerMovesOnPellet() throws ClassNotFoundException {
        Game game = startGameWithMap(GhostMapParser.getMapNameForTest());
        Player player = getPlayer(game);

        for (Map.Entry<Direction, Direction> directionEntry : oppositeDirection.entrySet()) {
            Direction direction = directionEntry.getKey();
            Direction opposite = directionEntry.getValue();

            Square expectedSquare = player.getSquare().getSquareAt(direction);
            assert expectedSquare.isAccessibleTo(player)
                : "Expected square in direction " + direction + " to be accessible";
            assert expectedSquare.getOccupants().size() == 1 && expectedSquare.getOccupants().get(0) instanceof Pellet
                : "Expected to find pellet in direction" + direction;
            Pellet pellet = (Pellet) expectedSquare.getOccupants().get(0);
            int expectedScore = player.getScore() + pellet.getValue();

            // execute and observe
            game.move(player, direction);
            assertThat(player.getSquare()).isEqualTo(expectedSquare); // then player can move to that square.
            assertThat(player.getScore()).isEqualTo(expectedScore); // then I earn the points for the pellet.
            assertFalse(expectedSquare.getOccupants().contains(pellet), "Visited Square still contains pellet!");
            assertFalse(pellet.hasSquare()); // then the pellet disappears from that square.

            // step back to try another direction
            game.move(player, opposite);
        }
        assert game.getLevel().remainingPellets() == 0 : "Expected player to consume all pellets";
    }

    /**
     * Verify that Scenario S2.2 holds true.
     *
     * @throws ClassNotFoundException should not be thrown.
     */
    @Test
    public void test_playerMovesOnEmptySquare() throws ClassNotFoundException {
        Game game = startGameWithMap(GhostMapParser.getMapNameForTest());
        Player player = getPlayer(game);

        for (Map.Entry<Direction, Direction> directionEntry : oppositeDirection.entrySet()) {
            Direction direction = directionEntry.getKey();
            Direction opposite = directionEntry.getValue();

            Square expectedSquare = player.getSquare().getSquareAt(direction);
            assert expectedSquare.isAccessibleTo(player)
                : "Expected square in direction " + direction + " to be accessible";
            assert expectedSquare.getOccupants().size() == 0
                : "Expected to find an empty square in direction" + direction;
            int expectedScore = player.getScore();

            // execute and observe
            game.move(player, direction);
            assertThat(player.getSquare()).isEqualTo(expectedSquare); // then player can move to that square.
            assertThat(player.getScore()).isEqualTo(expectedScore); // then my points remain the same.

            // step back to try another direction
            game.move(player, opposite);
        }
    }

    /**
     * Verify that Scenario S2.3 holds true.
     *
     * @throws ClassNotFoundException should not be thrown.
     */
    @Test
    public void test_playerMoveFails() throws ClassNotFoundException {
        Game game = startGameWithMap(GhostMapParser.getMapNameForTest());
        Player player = getPlayer(game);

        for (Direction direction : oppositeDirection.keySet()) {
            Square previousSquare = player.getSquare();

            // execute and observe
            game.move(player, direction);
            assertThat(player.getSquare()).isEqualTo(previousSquare); // then the move is not conducted.
        }
    }
}
