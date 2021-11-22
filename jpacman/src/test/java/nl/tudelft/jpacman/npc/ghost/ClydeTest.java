package nl.tudelft.jpacman.npc.ghost;

import java.io.IOException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test functionality of the {@link nl.tudelft.jpacman.npc.ghost.Clyde} class.
 */
public class ClydeTest {
    private GhostMapParser mapParser;
    private PlayerFactory playerFactory;

    /**
     * Prepares mapParser and playerFactory.
     */
    @BeforeEach
    public void setUp() {
        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory, new DefaultPointCalculator());
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        mapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        playerFactory = new PlayerFactory(pacManSprites);
    }

    /**
     * Verifies that no move is made when there is no Player on the board.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenNoPlayerFound_thenNoMove() throws ClassNotFoundException, IOException {
        Level level = mapParser.parseMap(GhostMapParser.getMapNameForTest());
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertThat(clyde.nextAiMove().isEmpty()).isTrue();
    }

    /**
     * Verifies that Clyde moves in the opposite direction of the shortest path to the closest player
     * (which isn't necessarily pointing away from the player)
     * when he is 8 or fewer tiles away along that path.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenPlayerInShynessRange_thenRetreat() throws ClassNotFoundException, IOException {
        Level level = mapParser.parseMap(GhostMapParser.getMapNameForTest());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertThat(clyde.nextAiMove().orElse(null)).isEqualTo(Direction.WEST);
    }

    /**
     * Verifies that Clyde moves in the direction of the shortest path to the closest player
     * when he is more than 8 tiles along that path.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenPlayerOutOfShynessRange_thenApproach() throws ClassNotFoundException, IOException {
        Level level = mapParser.parseMap(GhostMapParser.getMapNameForTest());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertThat(clyde.nextAiMove().orElse(null)).isEqualTo(Direction.WEST);
    }

    /**
     * Verifies that no move is made when there is no valid path to the nearest Player on the board.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenNoPathFound_thenNoMove() throws ClassNotFoundException, IOException {
        Level level = mapParser.parseMap(GhostMapParser.getMapNameForTest());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertThat(clyde.nextAiMove().isEmpty()).isTrue();
    }
}
