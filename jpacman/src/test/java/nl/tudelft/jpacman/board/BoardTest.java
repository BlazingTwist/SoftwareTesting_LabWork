package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * A Test for validating behaviour of the {@link Board} class.
 */
public class BoardTest {
    /**
     * .
     * Test that the board
     * - contains as many rows / columns as expected
     * - contains the provided square(s)
     * - is invariant
     */
    @Test
    public void createValidBoard() {
        BasicSquare square = new BasicSquare();
        Square[][] grid = new Square[][]{
            {square}
        };
        Board board = new Board(grid);
        assertThat(board.getHeight()).isEqualTo(1);
        assertThat(board.getWidth()).isEqualTo(1);
        assertThat(board.squareAt(0, 0)).isEqualTo(square);
        assertThat(board.invariant()).isTrue();
    }

    /**
     * Executes a boundary test for the `withinBorders` method.
     *
     * @param x        the x coordinate to check
     * @param y        the y coordinate to check
     * @param width    the board width
     * @param height   the board height
     * @param expected the expected result of `withinBorders`
     */
    @ParameterizedTest
    @CsvSource({
        "0, 2, 2, 11, TRUE",
        "-1, 4, 3, 10, FALSE",
        "3, 5, 4, 9, TRUE",
        "5, 6, 5, 8, FALSE",
        "1, 0, 6, 7, TRUE",
        "2, -1, 7, 6, FALSE",
        "4, 4, 8, 5, TRUE",
        "6, 4, 9, 4, FALSE",
        "0, 1, 1, 3, TRUE",
        "0, 0, 0, 0, FALSE",
        "6, 0, 10, 1, TRUE",
        "9, 0, 11, 0, FALSE"
    })
    public void boundaryTest_withinBorders(int x, int y, int width, int height, boolean expected) {
        BasicSquare square = new BasicSquare();
        Square[][] grid = new Square[width][height];
        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
            for (int heightIndex = 0; heightIndex < height; heightIndex++) {
                grid[widthIndex][heightIndex] = square;
            }
        }
        Board board = new Board(grid);

        assertThat(board.withinBorders(x, y)).isEqualTo(expected);
    }
}
