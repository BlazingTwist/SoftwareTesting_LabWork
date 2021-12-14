package nl.tudelft.jpacman.level;

import java.util.ArrayList;
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

    private PointCalculator pointCalculatorMock = null;
    private List<CollisionMap> collisionMapsToTest = null;

    /**
     * Called before every Test.
     */
    @BeforeEach
    public void setUp() {
        pointCalculatorMock = Mockito.mock(PointCalculator.class);
        collisionMapsToTest = new ArrayList<>();
        collisionMapsToTest.add(Mockito.spy(new PlayerCollisions(pointCalculatorMock)));
        collisionMapsToTest.add(Mockito.spy(new DefaultPlayerInteractionMap(pointCalculatorMock)));
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

                collisionMapsToTest.forEach(map -> map.collide(playerA, playerB));
            }
        }

        verify(pointCalculatorMock, times(0)).collidedWithAGhost(any(), any());
        verify(pointCalculatorMock, times(0)).consumedAPellet(any(), any());
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

                collisionMapsToTest.forEach(map -> {
                    map.collide(player, pellet);
                    map.collide(pellet, player);
                });
                collisionCounter += 2;
            }
        }

        verify(pointCalculatorMock, times(0)).collidedWithAGhost(any(), any());

        verify(pointCalculatorMock, times(collisionCounter * collisionMapsToTest.size()))
            .consumedAPellet(any(), any());
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

                collisionMapsToTest.forEach(map -> map.collide(ghostA, ghostB));
            }
        }

        verify(pointCalculatorMock, times(0)).collidedWithAGhost(any(), any());
        verify(pointCalculatorMock, times(0)).consumedAPellet(any(), any());
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

                collisionMapsToTest.forEach(map -> {
                    map.collide(ghost, pellet);
                    map.collide(pellet, ghost);
                });
            }
        }

        verify(pointCalculatorMock, times(0)).collidedWithAGhost(any(), any());
        verify(pointCalculatorMock, times(0)).consumedAPellet(any(), any());
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

                collisionMapsToTest.forEach(map -> {
                    map.collide(mover, collisionLessUnit);
                    map.collide(collisionLessUnit, mover);
                });
            }
        });

        verify(pointCalculatorMock, times(0)).collidedWithAGhost(any(), any());
        verify(pointCalculatorMock, times(0)).consumedAPellet(any(), any());
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

                collisionMapsToTest.forEach(map -> {
                    map.collide(player, ghost);
                    map.collide(ghost, player);
                });
                collisionCounter += 2;
            }
        }

        verify(pointCalculatorMock, times(collisionCounter * collisionMapsToTest.size()))
            .collidedWithAGhost(any(), any());

        verify(pointCalculatorMock, times(0)).consumedAPellet(any(), any());
    }
}
