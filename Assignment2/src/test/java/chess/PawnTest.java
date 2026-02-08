package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void testGetters() {
        Pawn pawn = new Pawn(1, 3, Color.WHITE);
        assertEquals(1, pawn.getRow());
        assertEquals(3, pawn.getColumn());
        assertEquals(Color.WHITE, pawn.getColor());
    }

    @Test
    public void testWhitePawnMoveOneForward() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertTrue(pawn.canMove(4, 3));
    }

    @Test
    public void testWhitePawnMoveTwoFromStart() {
        Pawn pawn = new Pawn(1, 3, Color.WHITE);
        assertTrue(pawn.canMove(2, 3));
        assertTrue(pawn.canMove(3, 3));
    }

    @Test
    public void testWhitePawnCannotMoveTwoFromNonStart() {
        Pawn pawn = new Pawn(2, 3, Color.WHITE);
        assertTrue(pawn.canMove(3, 3));
        assertFalse(pawn.canMove(4, 3));
    }

    @Test
    public void testWhitePawnCannotMoveBackward() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(pawn.canMove(2, 3));
    }

    @Test
    public void testBlackPawnMoveOneForward() {
        Pawn pawn = new Pawn(5, 3, Color.BLACK);
        assertTrue(pawn.canMove(4, 3));
    }

    @Test
    public void testBlackPawnMoveTwoFromStart() {
        Pawn pawn = new Pawn(6, 3, Color.BLACK);
        assertTrue(pawn.canMove(5, 3));
        assertTrue(pawn.canMove(4, 3));
    }

    @Test
    public void testBlackPawnCannotMoveTwoFromNonStart() {
        Pawn pawn = new Pawn(5, 3, Color.BLACK);
        assertTrue(pawn.canMove(4, 3));
        assertFalse(pawn.canMove(3, 3));
    }

    @Test
    public void testBlackPawnCannotMoveBackward() {
        Pawn pawn = new Pawn(5, 3, Color.BLACK);
        assertFalse(pawn.canMove(6, 3));
    }

    @Test
    public void testPawnCannotMoveDiagonally() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(pawn.canMove(4, 4));
        assertFalse(pawn.canMove(4, 2));
    }

    @Test
    public void testPawnCannotMoveHorizontally() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(pawn.canMove(3, 4));
    }

    @Test
    public void testCannotMoveToSamePosition() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(pawn.canMove(3, 3));
    }

    @Test
    public void testWhitePawnCanKillDiagonally() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        Rook blackRook1 = new Rook(4, 4, Color.BLACK);
        Rook blackRook2 = new Rook(4, 2, Color.BLACK);
        assertTrue(pawn.canKill(blackRook1));
        assertTrue(pawn.canKill(blackRook2));
    }

    @Test
    public void testBlackPawnCanKillDiagonally() {
        Pawn pawn = new Pawn(5, 3, Color.BLACK);
        Rook whiteRook1 = new Rook(4, 4, Color.WHITE);
        Rook whiteRook2 = new Rook(4, 2, Color.WHITE);
        assertTrue(pawn.canKill(whiteRook1));
        assertTrue(pawn.canKill(whiteRook2));
    }

    @Test
    public void testPawnCannotKillStraight() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        Rook blackRook = new Rook(4, 3, Color.BLACK);
        assertFalse(pawn.canKill(blackRook));
    }

    @Test
    public void testPawnCannotKillSameColor() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        Rook whiteRook = new Rook(4, 4, Color.WHITE);
        assertFalse(pawn.canKill(whiteRook));
    }

    @Test
    public void testPawnCannotKillBackward() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        Rook blackRook = new Rook(2, 4, Color.BLACK);
        assertFalse(pawn.canKill(blackRook));
    }

    @Test
    public void testPawnCannotKillTwoAway() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        Rook blackRook = new Rook(4, 5, Color.BLACK);
        assertFalse(pawn.canKill(blackRook));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhitePawnCannotBeCreatedInRow0() {
        new Pawn(0, 3, Color.WHITE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlackPawnCannotBeCreatedInRow7() {
        new Pawn(7, 3, Color.BLACK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPosition() {
        new Pawn(8, 3, Color.WHITE);
    }

    @Test
    public void testCanKillNull() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(pawn.canKill(null));
    }
}
