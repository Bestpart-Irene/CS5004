package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest {

    @Test
    public void testGetters() {
        Knight knight = new Knight(4, 4, Color.BLACK);
        assertEquals(4, knight.getRow());
        assertEquals(4, knight.getColumn());
        assertEquals(Color.BLACK, knight.getColor());
    }

    @Test
    public void testCanMoveLShape() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        assertTrue(knight.canMove(6, 5));
        assertTrue(knight.canMove(6, 3));
        assertTrue(knight.canMove(2, 5));
        assertTrue(knight.canMove(2, 3));
        assertTrue(knight.canMove(5, 6));
        assertTrue(knight.canMove(5, 2));
        assertTrue(knight.canMove(3, 6));
        assertTrue(knight.canMove(3, 2));
    }

    @Test
    public void testCannotMoveDiagonally() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        assertFalse(knight.canMove(5, 5));
        assertFalse(knight.canMove(6, 6));
    }

    @Test
    public void testCannotMoveHorizontally() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        assertFalse(knight.canMove(4, 6));
    }

    @Test
    public void testCannotMoveVertically() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        assertFalse(knight.canMove(6, 4));
    }

    @Test
    public void testCannotMoveToSamePosition() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        assertFalse(knight.canMove(4, 4));
    }

    @Test
    public void testCannotMoveOutOfBounds() {
        Knight knight = new Knight(0, 0, Color.WHITE);
        assertFalse(knight.canMove(-2, 1));
    }

    @Test
    public void testCanKillOpponent() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        Rook blackRook = new Rook(6, 5, Color.BLACK);
        assertTrue(knight.canKill(blackRook));
    }

    @Test
    public void testCannotKillSameColor() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        Rook whiteRook = new Rook(6, 5, Color.WHITE);
        assertFalse(knight.canKill(whiteRook));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNegativePosition() {
        new Knight(-1, 4, Color.WHITE);
    }
}
