import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Rook.
 */
public class RookTest {
    
    @Test
    public void testConstructorAndGetters() {
        Rook rook = new Rook(0, 0, Color.WHITE);
        assertEquals(0, rook.getRow());
        assertEquals(0, rook.getColumn());
        assertEquals(Color.WHITE, rook.getColor());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidPosition() {
        new Rook(10, 3, Color.BLACK);
    }
    
    @Test
    public void testCanMoveHorizontalRight() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertTrue(rook.canMove(3, 4));
        assertTrue(rook.canMove(3, 5));
        assertTrue(rook.canMove(3, 7));
    }
    
    @Test
    public void testCanMoveHorizontalLeft() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertTrue(rook.canMove(3, 2));
        assertTrue(rook.canMove(3, 1));
        assertTrue(rook.canMove(3, 0));
    }
    
    @Test
    public void testCanMoveVerticalUp() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertTrue(rook.canMove(4, 3));
        assertTrue(rook.canMove(5, 3));
        assertTrue(rook.canMove(7, 3));
    }
    
    @Test
    public void testCanMoveVerticalDown() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertTrue(rook.canMove(2, 3));
        assertTrue(rook.canMove(1, 3));
        assertTrue(rook.canMove(0, 3));
    }
    
    @Test
    public void testCannotMoveDiagonal() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertFalse(rook.canMove(4, 4));
        assertFalse(rook.canMove(5, 5));
        assertFalse(rook.canMove(2, 2));
        assertFalse(rook.canMove(1, 1));
    }
    
    @Test
    public void testCannotMoveInvalidPattern() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertFalse(rook.canMove(5, 4));
        assertFalse(rook.canMove(4, 5));
        assertFalse(rook.canMove(6, 4));
    }
    
    @Test
    public void testCannotMoveToSamePosition() {
        Rook rook = new Rook(3, 3, Color.WHITE);
        assertFalse(rook.canMove(3, 3));
    }
    
    @Test
    public void testCannotMoveOutOfBounds() {
        Rook rook = new Rook(7, 7, Color.WHITE);
        assertFalse(rook.canMove(8, 7));
        assertFalse(rook.canMove(7, 8));
    }
    
    @Test
    public void testCanMoveEntireRow() {
        Rook rook = new Rook(4, 4, Color.BLACK);
        for (int col = 0; col <= 7; col++) {
            if (col != 4) {
                assertTrue(rook.canMove(4, col));
            }
        }
    }
    
    @Test
    public void testCanMoveEntireColumn() {
        Rook rook = new Rook(4, 4, Color.BLACK);
        for (int row = 0; row <= 7; row++) {
            if (row != 4) {
                assertTrue(rook.canMove(row, 4));
            }
        }
    }
    
    @Test
    public void testCanKillOpponentHorizontal() {
        Rook whiteRook = new Rook(3, 3, Color.WHITE);
        Bishop blackBishop = new Bishop(3, 7, Color.BLACK);
        assertTrue(whiteRook.canKill(blackBishop));
    }
    
    @Test
    public void testCanKillOpponentVertical() {
        Rook whiteRook = new Rook(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(7, 3, Color.BLACK);
        assertTrue(whiteRook.canKill(blackKnight));
    }
    
    @Test
    public void testCannotKillSameColorPiece() {
        Rook whiteRook = new Rook(3, 3, Color.WHITE);
        Pawn whitePawn = new Pawn(3, 5, Color.WHITE);
        assertFalse(whiteRook.canKill(whitePawn));
    }
    
    @Test
    public void testCannotKillPieceOnDiagonal() {
        Rook whiteRook = new Rook(3, 3, Color.WHITE);
        Bishop blackBishop = new Bishop(5, 5, Color.BLACK);
        assertFalse(whiteRook.canKill(blackBishop));
    }
}