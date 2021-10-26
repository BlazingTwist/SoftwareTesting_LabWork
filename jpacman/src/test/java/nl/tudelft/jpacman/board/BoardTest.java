package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    public void createValidBoard() {
        BasicSquare square = new BasicSquare();
        Square[][] grid = new Square[][]{
            {square}
        };
        Board board = new Board(grid);
        assertThat(board.getHeight()).isEqualTo(1);
        assertThat(board.getWidth()).isEqualTo(1);
        assertThat(board.squareAt(0,0)).isEqualTo(square);
        assertThat(board.invariant()).isTrue();
    }

    @Test
    public void createInvalidBoard(){
        Square[][] grid = new Square[2][1];
        BasicSquare nonNullSquare = new BasicSquare();
        grid[0][0] = nonNullSquare;
        grid[1][0] = null;
        Board board = new Board(grid);
        assertThat(board.squareAt(0,0)).isEqualTo(nonNullSquare);
        assertThat(board.squareAt(1,0)).isEqualTo(null);
    }
}
