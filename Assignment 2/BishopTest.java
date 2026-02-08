import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Bishop.
 */
public class BishopTest {
    
    @Test
    public void testConstructorAndGetters() {
        Bishop bishop = new Bishop(3, 4, Color.WHITE);
        assertEquals(3, bishop.getRow());
        assertEquals(4, bishop.getColumn());
        assertEquals(Color.WHITE, bishop.getColor());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidRowNegative() {
        new Bishop(-1, 4, Color.BLACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidRowTooHigh() {
        new Bishop(8, 4, Color.BLACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidColumnNegative() {
        new Bishop(3, -1, Color.WHITE);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidColumnTooHigh() {
        new Bishop(3, 8, Color.WHITE);
    }
    
    @Test
    public void testCanMoveDiagonalUpRight() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertTrue(bishop.canMove(4, 4));
        assertTrue(bishop.canMove(5, 5));
        assertTrue(bishop.canMove(7, 7));
    }
    
    @Test
    public void testCanMoveDiagonalUpLeft() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertTrue(bishop.canMove(4, 2));
        assertTrue(bishop.canMove(5, 1));
        assertTrue(bishop.canMove(6, 0));
    }
    
    @Test
    public void testCanMoveDiagonalDownRight() {
        Bishop bishop = new Bishop(3, 3, Color.BLACK);
        assertTrue(bishop.canMove(2, 4));
        assertTrue(bishop.canMove(1, 5));
        assertTrue(bishop.canMove(0, 6));
    }
    
    @Test
    public void testCanMoveDiagonalDownLeft() {
        Bishop bishop = new Bishop(3, 3, Color.BLACK);
        assertTrue(bishop.canMove(2, 2));
        assertTrue(bishop.canMove(1, 1));
        assertTrue(bishop.canMove(0, 0));
    }
    
    @Test
    public void testCannotMoveHorizontal() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(3, 5));
        assertFalse(bishop.canMove(3, 0));
        assertFalse(bishop.canMove(3, 7));
    }
    
    @Test
    public void testCannotMoveVertical() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(5, 3));
        assertFalse(bishop.canMove(0, 3));
        assertFalse(bishop.canMove(7, 3));
    }
    
    @Test
    public void testCannotMoveNonDiagonal() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(5, 4));
        assertFalse(bishop.canMove(4, 6));
        assertFalse(bishop.canMove(6, 4));
    }
    
    @Test
    public void testCannotMoveToSamePosition() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(3, 3));
    }
    
    @Test
    public void testCannotMoveOutOfBounds() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canMove(-1, -1));
        assertFalse(bishop.canMove(8, 8));
        assertFalse(bishop.canMove(10, 10));
    }
    
    @Test
    public void testCanKillOpponentPiece() {
        Bishop whiteBishop = new Bishop(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(5, 5, Color.BLACK);
        assertTrue(whiteBishop.canKill(blackKnight));
    }
    
    @Test
    public void testCannotKillSameColorPiece() {
        Bishop whiteBishop = new Bishop(3, 3, Color.WHITE);
        Rook whiteRook = new Rook(5, 5, Color.WHITE);
        assertFalse(whiteBishop.canKill(whiteRook));
    }
    
    @Test
    public void testCannotKillPieceNotOnDiagonal() {
        Bishop whiteBishop = new Bishop(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(3, 5, Color.BLACK);
        assertFalse(whiteBishop.canKill(blackKnight));
    }
    
    @Test
    public void testCannotKillNull() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        assertFalse(bishop.canKill(null));
    }
}