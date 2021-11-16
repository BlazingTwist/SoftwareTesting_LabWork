package nl.tudelft.jpacman.board;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.GhostMapParser;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        assertThat(unit.hasSquare()).isFalse();
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        Square theSquare = new BasicSquare();
        unit.occupy(theSquare);
        assertThat(unit.getSquare()).isEqualTo(theSquare);
        assertThat(theSquare.getOccupants()).contains(unit);
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        Square theSquare = new BasicSquare();
        unit.occupy(theSquare);
        unit.occupy(theSquare);
        assertThat(unit.getSquare()).isEqualTo(theSquare);
        assertThat(theSquare.getOccupants()).contains(unit);
    }

    /**
     * Verifies that the squaresAheadOf method wraps at the border of the map.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_thatSquaresAheadOf_producesWrapping() throws ClassNotFoundException, IOException {
        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory, new DefaultPointCalculator());
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser mapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);

        Level level = mapParser.parseMap(GhostMapParser.getMapNameForTest());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

        player.setDirection(Direction.NORTH);
        final int amountToLookAhead = 3; // the amount of tiles of travel to reach our marker
        Square square = player.squaresAheadOf(amountToLookAhead);
        boolean occupantsContainClyde = square.getOccupants().stream()
            .anyMatch(unit -> unit.getClass().isAssignableFrom(Clyde.class));
        assertThat(occupantsContainClyde).isTrue();
    }
}
