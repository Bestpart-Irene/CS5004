package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void testGetters() {
        Rook rook = new Rook(0, 0, Color.WHITE);
        assertEquals(0, rook.getRow());
        assertEquals(0, rook.getColumn());
        assertEquals(Color.WHITE, rook.getColor());
    }

    @Test
    public void testCanMoveHorizontally() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertTrue(rook.canMove(3, 0));
        assertTrue(rook.canMove(3, 7));
        assertTrue(rook.canMove(3, 5));
    }

    @Test
    public void testCanMoveVertically() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertTrue(rook.canMove(0, 3));
        assertTrue(rook.canMove(7, 3));
        assertTrue(rook.canMove(5, 3));
    }

    @Test
    public void testCannotMoveDiagonally() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertFalse(rook.canMove(5, 5));
        assertFalse(rook.canMove(1, 1));
    }

    @Test
    public void testCannotMoveToSamePosition() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertFalse(rook.canMove(3, 3));
    }

    @Test
    public void testCannotMoveOutOfBounds() {
        Rook rook = new Rook(7, 7, Color.WHITE);
        assertFalse(rook.canMove(7, 8));
        assertFalse(rook.canMove(8, 7));
    }

    @Test
    public void testCanKillOpponent() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(3, 7, Color.BLACK);
        assertTrue(rook.canKill(blackKnight));
    }

    @Test
    public void testCannotKillSameColor() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        Knight whiteKnight = new Knight(3, 7, Color.WHITE);
        assertFalse(rook.canKill(whiteKnight));
    }

    @Test
    public void testCannotKillDiagonally() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(5, 5, Color.BLACK);
        assertFalse(rook.canKill(blackKnight));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPosition() {
        new Rook(8, 0, Color.WHITE);
    }
}
