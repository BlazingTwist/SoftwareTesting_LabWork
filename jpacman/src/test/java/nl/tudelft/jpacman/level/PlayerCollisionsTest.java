package nl.tudelft.jpacman.level;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.ghost.Inky;
import nl.tudelft.jpacman.npc.ghost.Pinky;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * A test for all possible collisions that can occur during the game.
 */
public class PlayerCollisionsTest {

    private static final List<Class<? extends Unit>> PLAYERS = List.of(Player.class);
    private static final List<Class<? extends Unit>> GHOSTS = Arrays.asList(
        Blinky.class,
        Clyde.class,
        Ghost.class,
        Inky.class,
        Pinky.class);
    private static final List<Class<? extends Unit>> COLLISION_LESS = List.of(Unit.class);
    private static final List<Class<? extends Unit>> PELLETS = List.of(Pellet.class);

    private PlayerCollisions playerCollisions = null;

    /**
     * Called before every Test.
     */
    @BeforeEach
    public void setUp() {
        playerCollisions = Mockito.spy(new PlayerCollisions(
            Mockito.mock(PointCalculator.class)
        ));
    }

    /**
     * Verify that Players don't collide with another.
     */
    @Test
    public void test_collide_playerOnPlayer() {
        for (Class<? extends Unit> playerClassA : PLAYERS) {
            Unit playerA = Mockito.mock(playerClassA);
            for (Class<? extends Unit> playerClassB : PLAYERS) {
                Unit playerB = Mockito.mock(playerClassB);

                playerCollisions.collide(playerA, playerB);
            }
        }

        verify(playerCollisions, times(0)).playerVersusGhost(any(), any());
        verify(playerCollisions, times(0)).playerVersusPellet(any(), any());
    }

    /**
     * Verify that Players correctly collide with Pellets.
     */
    @Test
    public void test_collide_playerOnPellet() {
        int collisionCounter = 0;
        for (Class<? extends Unit> playerClass : PLAYERS) {
            Unit player = Mockito.mock(playerClass);
            for (Class<? extends Unit> pelletClass : PELLETS) {
                Unit pellet = Mockito.mock(pelletClass);

                playerCollisions.collide(player, pellet);
                playerCollisions.collide(pellet, player);
                collisionCounter += 2;
            }
        }

        verify(playerCollisions, times(0)).playerVersusGhost(any(), any());
        verify(playerCollisions, times(collisionCounter)).playerVersusPellet(any(), any());
    }

    /**
     * Verify that Ghosts don't collide with another.
     */
    @Test
    public void test_collide_ghostOnGhost() {
        for (Class<? extends Unit> ghostClassA : GHOSTS) {
            Unit ghostA = Mockito.mock(ghostClassA);
            for (Class<? extends Unit> ghostClassB : GHOSTS) {
                Unit ghostB = Mockito.mock(ghostClassB);

                playerCollisions.collide(ghostA, ghostB);
            }
        }

        verify(playerCollisions, times(0)).playerVersusGhost(any(), any());
        verify(playerCollisions, times(0)).playerVersusPellet(any(), any());
    }

    /**
     * Verify that Ghosts don't collide with Pellets.
     */
    @Test
    public void test_collide_ghostOnPellet() {
        for (Class<? extends Unit> ghostClass : GHOSTS) {
            Unit ghost = Mockito.mock(ghostClass);
            for (Class<? extends Unit> pelletClass : PELLETS) {
                Unit pellet = Mockito.mock(pelletClass);

                playerCollisions.collide(ghost, pellet);
                playerCollisions.collide(pellet, ghost);
            }
        }

        verify(playerCollisions, times(0)).playerVersusGhost(any(), any());
        verify(playerCollisions, times(0)).playerVersusPellet(any(), any());
    }

    /**
     * Verify that collisionLess Units don't collide with Players and Ghosts.
     */
    @Test
    public void test_collide_movingOnCollisionLess() {
        Stream.concat(PLAYERS.stream(), GHOSTS.stream()).forEach(moverClass -> {
            Unit mover = Mockito.mock(moverClass);
            for (Class<? extends Unit> collisionClass : COLLISION_LESS) {
                Unit collisionLessUnit = Mockito.mock(collisionClass);

                playerCollisions.collide(mover, collisionLessUnit);
                playerCollisions.collide(collisionLessUnit, mover);
            }
        });

        verify(playerCollisions, times(0)).playerVersusGhost(any(), any());
        verify(playerCollisions, times(0)).playerVersusPellet(any(), any());
    }

    /**
     * Verify that Players and Ghosts collide with each other.
     */
    @Test
    public void test_collide_playerOnGhost() {
        int collisionCounter = 0;
        for (Class<? extends Unit> playerClass : PLAYERS) {
            Unit player = Mockito.mock(playerClass);
            for (Class<? extends Unit> ghostClass : GHOSTS) {
                Unit ghost = Mockito.mock(ghostClass);

                playerCollisions.collide(player, ghost);
                playerCollisions.collide(ghost, player);
                collisionCounter += 2;
            }
        }

        verify(playerCollisions, times(collisionCounter)).playerVersusGhost(any(), any());
        verify(playerCollisions, times(0)).playerVersusPellet(any(), any());
    }
}
