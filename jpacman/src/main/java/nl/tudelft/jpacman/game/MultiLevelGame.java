package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import java.util.List;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

/**
 * A game with one player and multiple levels.
 */
public class MultiLevelGame extends Game {

    /**
     * The player of this game.
     */
    private final Player player;

    /**
     * Array of levels in order.
     */
    private final Level[] levels;

    /**
     * The index of the level currently being played.
     */
    private int currentLevelIndex = 0;

    /**
     * Creates a new game.
     *
     * @param player          The player.
     * @param levels          The levels in order.
     * @param pointCalculator The way to calculate points upon collisions.
     */
    protected MultiLevelGame(Player player, Level[] levels, PointCalculator pointCalculator) {
        super(pointCalculator);

        assert player != null;
        assert levels != null && levels.length > 0;

        this.player = player;
        this.levels = levels.clone();
        this.levels[currentLevelIndex].registerPlayer(player);
    }

    /**
     * @return An immutable list of the participants of this game.
     */
    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    /**
     * @return The level currently being played.
     */
    @Override
    public Level getLevel() {
        if (currentLevelIndex >= levels.length) {
            return null;
        }
        return levels[currentLevelIndex];
    }

    /**
     * Changes to the next level.
     *
     * @return true if a next level was found
     */
    public boolean nextLevel() {
        if ((currentLevelIndex + 1) >= levels.length) {
            return false;
        }
        currentLevelIndex++;
        return true;
    }
}
