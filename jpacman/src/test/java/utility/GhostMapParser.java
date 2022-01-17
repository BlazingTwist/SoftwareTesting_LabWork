package utility;

import java.util.List;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;

/**
 * A test helper utility for writing the ghost unit tests.
 * A useful method for retrieving ghosts from your map would be:
 * findUnitInBoard in the Navigation class.
 */
public final class GhostMapParser extends MapParser {
    /**
     * Maps for tests are stored in the resources directory as `[NameOfTestClass]/[NameOfTest].map`.
     *
     * @return the relative path to the test-map
     * @throws java.lang.ClassNotFoundException should not be thrown
     */
    public static String getMapNameForTest() throws ClassNotFoundException {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String simpleClassName = Class.forName(className).getSimpleName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return "/" + simpleClassName + "/" + methodName + ".map";
    }

    private final GhostFactory ghostFactory;

    /**
     * Creates a new enhanced map parser.
     * This map parser allows users to specify which ghost should be placed
     * at an exact location.
     *
     * @param levelFactory The factory providing the NPC objects and the level.
     * @param boardFactory The factory to create board elements.
     * @param ghostFactory the factory to create the ghosts.
     */
    public GhostMapParser(LevelFactory levelFactory, BoardFactory boardFactory,
                          GhostFactory ghostFactory) {
        super(levelFactory, boardFactory);
        this.ghostFactory = ghostFactory;
    }

    //This method only supports clyde for now
    //You should add extra cases for ghosts you need.
    @Override
    protected void addSquare(Square[][] grid, List<Ghost> ghosts,
                             List<Square> startPositions, int x, int y, char c) {
        switch (c) {
            case 'C':
                grid[x][y] = makeGhostSquare(ghosts, ghostFactory.createClyde());
                break;
            case 'B':
                grid[x][y] = makeGhostSquare(ghosts, ghostFactory.createBlinky());
                break;
            case 'S':
                grid[x][y] = makeGhostSquare(ghosts, ghostFactory.createPinky());
                break;
            case 'I':
                grid[x][y] = makeGhostSquare(ghosts, ghostFactory.createInky());
                break;
            default:
                super.addSquare(grid, ghosts, startPositions, x, y, c);
        }
    }
}
