package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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
}
