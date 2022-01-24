package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.level.Level;

/**
 * Creates and launches the JPacMan UI.
 */
public class MultiLevelLauncher extends Launcher {

    /**
     * Creates a new game using the level from {@link #makeLevel()}.
     *
     * @return a new Game.
     */
    @Override
    public Game makeGame() {
        GameFactory gf = getGameFactory();
        Level[] levels = new Level[]{makeLevel()};
        setGame(gf.createMultiLevelGame(levels, loadPointCalculator()));
        return getGame();
    }

    /**
     * Main execution method for the Launcher.
     *
     * @param args The command line arguments - which are ignored.
     */
    public static void main(String[] args) {
        new MultiLevelLauncher().launch();
    }
}
