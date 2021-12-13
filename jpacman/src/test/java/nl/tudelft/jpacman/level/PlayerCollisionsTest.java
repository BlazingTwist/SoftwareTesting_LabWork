package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


/**
 * A test for all possible collisions that can occur during the game.
 */
public class PlayerCollisionsTest {

    PointCalculator pcMock = Mockito.mock(PointCalculator.class);
    Player playerMock = Mockito.mock(Player.class);
    Ghost ghostMock = Mockito.mock(Ghost.class);
    Pellet pelletMock = Mockito.mock(Pellet.class);
    Unit unitMock = Mockito.mock(Unit.class);

    PlayerCollisions pC = Mockito.spy(new PlayerCollisions(pcMock));

    /**
     *  Verifies that the collision between the player and another player isn´t recognized.
     */
    @Test
    public void testPlayervsPlayer(){

        pC.collide(ghostMock, playerMock);

        Mockito.verify(pC, Mockito.times(0)).playerVersusPellet(playerMock, pelletMock);
        Mockito.verify(pC, Mockito.times(0)).playerVersusGhost(playerMock, ghostMock);
    }

    /**
     *  Verifies that the collision between an Moving-Unit(Player, Ghost) and an NonMoving-Unit(Pellets, BasicUnit, Unit) isn´t recognized.
     */
    @Test
    public void testMovingvsNonmoving(){

        pC.collide(playerMock, unitMock);

        Mockito.verify(pC, Mockito.times(0)).playerVersusPellet(playerMock, pelletMock);
        Mockito.verify(pC, Mockito.times(0)).playerVersusGhost(playerMock, ghostMock);
    }

    /**
     * Verifies that the collision between the player and a pellet is recognized.
     */
    @Test
    public void testPlayervsPellet(){

        pC.collide(playerMock, pelletMock);

        Mockito.verify(pC, Mockito.times(1)).playerVersusPellet(playerMock, pelletMock);
    }

    /**
     * Verifies that the collision between the player and a ghost is recognized.
     */
    @Test
    public void testPlayervsGhost(){

        pC.collide(playerMock, ghostMock);

        Mockito.verify(pC, Mockito.times(1)).playerVersusGhost(playerMock, ghostMock);
    }

    /**
     * Verifies that the collision between a ghost and another ghost isn´t recognized.
     */
    @Test
    public void testGhostvsGhost(){

        pC.collide(ghostMock,ghostMock);

        Mockito.verify(pC, Mockito.times(0)).playerVersusPellet(playerMock, pelletMock);
        Mockito.verify(pC, Mockito.times(0)).playerVersusGhost(playerMock, ghostMock);
    }

    /**
     * Verifies that the collision between a ghost and a pellet isn´t recognized.
     */
    @Test
    public void testGhostvsPellet(){

        pC.collide(playerMock, pelletMock);

        Mockito.verify(pC, Mockito.times(0)).playerVersusPellet(playerMock, pelletMock);
        Mockito.verify(pC, Mockito.times(0)).playerVersusGhost(playerMock, ghostMock);
    }
}
