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
import utility.GhostMapParser;

/**
 * Tests functionality of the {@link nl.tudelft.jpacman.npc.ghost.Inky} class.
 */
public class InkyTest {
    private GhostMapParser mapParser;
    private PlayerFactory playerFactory;

    private Player player;
    private Inky inky;

    /**
     * Called before every Test.
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

    private void loadMap(String mapName, boolean hasPlayer) throws IOException {
        Level level = mapParser.parseMap(mapName);
        if (hasPlayer) {
            player = playerFactory.createPacMan();
            level.registerPlayer(player);
        }

        inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assert inky != null;
    }

    /**
     * Verifies that no move is made when there is no Player on the board.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenNoPlayerFound_thenNoMove() throws ClassNotFoundException, IOException {
        loadMap(GhostMapParser.getMapNameForTest(), false);
        assertThat(inky.nextAiMove().isEmpty()).isTrue();
    }

    /**
     * Verifies that no move is made when Blinky is not on the board.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenNoBlinkyFound_thenNoMove() throws ClassNotFoundException, IOException {
        loadMap(GhostMapParser.getMapNameForTest(), true);
        assertThat(inky.nextAiMove().isEmpty()).isTrue();
    }

    /**
     * Verifies that Inky does not move when there is no valid path to the target
     * (e.g. path obstructed by a wall)
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenNoPathToTarget_thenNoMove() throws ClassNotFoundException, IOException {
        loadMap(GhostMapParser.getMapNameForTest(), true);
        player.setDirection(Direction.SOUTH);
        assertThat(inky.nextAiMove().isEmpty()).isTrue();
    }

    /**
     * Verifies that normal pathfinding without wrapping at borders is working as expected.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_validTargetPathWithoutWrapping() throws ClassNotFoundException, IOException {
        loadMap(GhostMapParser.getMapNameForTest(), true);
        player.setDirection(Direction.EAST);
        assertThat(inky.nextAiMove().orElse(null)).isEqualTo(Direction.NORTH);
    }

    /**
     * Verifies that pathfinding with wrapping at borders is working as expected.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_validTargetPathWithWrapping() throws ClassNotFoundException, IOException {
        loadMap(GhostMapParser.getMapNameForTest(), true);
        player.setDirection(Direction.EAST);
        assertThat(inky.nextAiMove().orElse(null)).isEqualTo(Direction.SOUTH);
    }

    /**
     * Verifies that Inky stops moving when he has reached his target Square.
     *
     * @throws ClassNotFoundException should not be thrown.
     * @throws IOException            should not be thrown.
     */
    @Test
    public void test_whenAlreadyAtTarget_thenNoMove() throws ClassNotFoundException, IOException {
        loadMap(GhostMapParser.getMapNameForTest(), true);
        player.setDirection(Direction.EAST);
        assertThat(inky.nextAiMove().isEmpty()).isTrue();
    }
}
