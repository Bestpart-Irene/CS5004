package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class BishopTest {

    @Test
    public void testGetters() {
        Bishop bishop = new Bishop(2, 3, Color.WHITE);
        assertEquals(2, bishop.getRow());
        assertEquals(3, bishop.getColumn());
        assertEquals(Color.WHITE, bishop.getColor());
    }

    @Test
    public void testCanMoveDiagonally() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertTrue(bishop.canMove(5, 5));
        assertTrue(bishop.canMove(1, 1));
        assertTrue(bishop.canMove(5, 1));
        assertTrue(bishop.canMove(1, 5));
        assertTrue(bishop.canMove(0, 0));
        assertTrue(bishop.canMove(6, 6));
    }

    @Test
    public void testCannotMoveHorizontally() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(3, 5));
        assertFalse(bishop.canMove(3, 0));
    }

    @Test
    public void testCannotMoveVertically() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(5, 3));
        assertFalse(bishop.canMove(0, 3));
    }

    @Test
    public void testCannotMoveToSamePosition() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(3, 3));
    }

    @Test
    public void testCannotMoveOutOfBounds() {
        Bishop bishop = new Bishop(0, 0, Color.WHITE);
        assertFalse(bishop.canMove(-1, -1));
        assertFalse(bishop.canMove(8, 8));
    }

    @Test
    public void testCanKillOpponent() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        Pawn blackPawn = new Pawn(5, 5, Color.BLACK);
        assertTrue(bishop.canKill(blackPawn));
    }

    @Test
    public void testCannotKillSameColor() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        Pawn whitePawn = new Pawn(5, 5, Color.WHITE);
        assertFalse(bishop.canKill(whitePawn));
    }

    @Test
    public void testCannotKillIfCannotMove() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        Pawn blackPawn = new Pawn(3, 5, Color.BLACK);
        assertFalse(bishop.canKill(blackPawn));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPosition() {
        new Bishop(8, 8, Color.WHITE);
    }
}
