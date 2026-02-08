package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueenTest {

    @Test
    public void testGetters() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertEquals(3, queen.getRow());
        assertEquals(3, queen.getColumn());
        assertEquals(Color.WHITE, queen.getColor());
    }

    @Test
    public void testCanMoveDiagonally() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(5, 5));
        assertTrue(queen.canMove(1, 1));
        assertTrue(queen.canMove(5, 1));
        assertTrue(queen.canMove(1, 5));
    }

    @Test
    public void testCanMoveHorizontally() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(3, 0));
        assertTrue(queen.canMove(3, 7));
    }

    @Test
    public void testCanMoveVertically() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(0, 3));
        assertTrue(queen.canMove(7, 3));
    }

    @Test
    public void testCannotMoveToSamePosition() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertFalse(queen.canMove(3, 3));
    }

    @Test
    public void testCannotMoveInvalidPattern() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertFalse(queen.canMove(5, 4));
        assertFalse(queen.canMove(4, 6));
    }

    @Test
    public void testCannotMoveOutOfBounds() {
        Queen queen = new Queen(7, 7, Color.WHITE);
        assertFalse(queen.canMove(8, 8));
    }

    @Test
    public void testCanKillOpponent() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        Bishop blackBishop = new Bishop(5, 5, Color.BLACK);
        assertTrue(queen.canKill(blackBishop));
    }

    @Test
    public void testCannotKillSameColor() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        Bishop whiteBishop = new Bishop(5, 5, Color.WHITE);
        assertFalse(queen.canKill(whiteBishop));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPosition() {
        new Queen(3, 8, Color.WHITE);
    }
}
