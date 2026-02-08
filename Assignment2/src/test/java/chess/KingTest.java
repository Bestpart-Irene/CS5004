package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class KingTest {

    @Test
    public void testGetters() {
        King king = new King(4, 4, Color.BLACK);
        assertEquals(4, king.getRow());
        assertEquals(4, king.getColumn());
        assertEquals(Color.BLACK, king.getColor());
    }

    @Test
    public void testCanMoveOneSquareAnyDirection() {
        King king = new King(4, 4, Color.WHITE);
        assertTrue(king.canMove(5, 5));
        assertTrue(king.canMove(5, 4));
        assertTrue(king.canMove(5, 3));
        assertTrue(king.canMove(4, 5));
        assertTrue(king.canMove(4, 3));
        assertTrue(king.canMove(3, 5));
        assertTrue(king.canMove(3, 4));
        assertTrue(king.canMove(3, 3));
    }

    @Test
    public void testCannotMoveMoreThanOneSquare() {
        King king = new King(4, 4, Color.WHITE);
        assertFalse(king.canMove(6, 6));
        assertFalse(king.canMove(6, 4));
        assertFalse(king.canMove(4, 6));
        assertFalse(king.canMove(2, 2));
    }

    @Test
    public void testCannotMoveToSamePosition() {
        King king = new King(4, 4, Color.WHITE);
        assertFalse(king.canMove(4, 4));
    }

    @Test
    public void testCannotMoveOutOfBounds() {
        King king = new King(0, 0, Color.WHITE);
        assertFalse(king.canMove(-1, 0));
        assertFalse(king.canMove(0, -1));
    }

    @Test
    public void testCanKillOpponent() {
        King king = new King(4, 4, Color.WHITE);
        Queen blackQueen = new Queen(5, 5, Color.BLACK);
        assertTrue(king.canKill(blackQueen));
    }

    @Test
    public void testCannotKillSameColor() {
        King king = new King(4, 4, Color.WHITE);
        Queen whiteQueen = new Queen(5, 5, Color.WHITE);
        assertFalse(king.canKill(whiteQueen));
    }

    @Test
    public void testCannotKillIfTooFar() {
        King king = new King(4, 4, Color.WHITE);
        Queen blackQueen = new Queen(6, 6, Color.BLACK);
        assertFalse(king.canKill(blackQueen));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPosition() {
        new King(-1, 4, Color.WHITE);
    }
}
