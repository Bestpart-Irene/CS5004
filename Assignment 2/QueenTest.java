import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Queen.
 */
public class QueenTest {
    
    @Test
    public void testConstructorAndGetters() {
        Queen queen = new Queen(0, 3, Color.BLACK);
        assertEquals(0, queen.getRow());
        assertEquals(3, queen.getColumn());
        assertEquals(Color.BLACK, queen.getColor());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidPosition() {
        new Queen(8, 3, Color.WHITE);
    }
    
    @Test
    public void testCanMoveHorizontalRight() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(3, 7));
        assertTrue(queen.canMove(3, 5));
        assertTrue(queen.canMove(3, 4));
    }
    
    @Test
    public void testCanMoveHorizontalLeft() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(3, 0));
        assertTrue(queen.canMove(3, 1));
        assertTrue(queen.canMove(3, 2));
    }
    
    @Test
    public void testCanMoveVerticalUp() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(7, 3));
        assertTrue(queen.canMove(5, 3));
        assertTrue(queen.canMove(4, 3));
    }
    
    @Test
    public void testCanMoveVerticalDown() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(0, 3));
        assertTrue(queen.canMove(1, 3));
        assertTrue(queen.canMove(2, 3));
    }
    
    @Test
    public void testCanMoveDiagonalUpRight() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(7, 7));
        assertTrue(queen.canMove(5, 5));
        assertTrue(queen.canMove(4, 4));
    }
    
    @Test
    public void testCanMoveDiagonalUpLeft() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertTrue(queen.canMove(6, 0));
        assertTrue(queen.canMove(5, 1));
        assertTrue(queen.canMove(4, 2));
    }
    
    @Test
    public void testCanMoveDiagonalDownRight() {
        Queen queen = new Queen(3, 3, Color.BLACK);
        assertTrue(queen.canMove(0, 6));
        assertTrue(queen.canMove(1, 5));
        assertTrue(queen.canMove(2, 4));
    }
    
    @Test
    public void testCanMoveDiagonalDownLeft() {
        Queen queen = new Queen(3, 3, Color.BLACK);
        assertTrue(queen.canMove(0, 0));
        assertTrue(queen.canMove(1, 1));
        assertTrue(queen.canMove(2, 2));
    }
    
    @Test
    public void testCannotMoveInvalidPattern() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertFalse(queen.canMove(5, 4)); // Not straight or diagonal
        assertFalse(queen.canMove(4, 6)); // Not straight or diagonal
        assertFalse(queen.canMove(6, 4)); // Not straight or diagonal
    }
    
    @Test
    public void testCannotMoveToSamePosition() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        assertFalse(queen.canMove(3, 3));
    }
    
    @Test
    public void testCannotMoveOutOfBounds() {
        Queen queen = new Queen(7, 7, Color.WHITE);
        assertFalse(queen.canMove(8, 7));
        assertFalse(queen.canMove(7, 8));
        assertFalse(queen.canMove(8, 8));
    }
    
    @Test
    public void testCanKillOpponentHorizontal() {
        Queen whiteQueen = new Queen(3, 3, Color.WHITE);
        Rook blackRook = new Rook(3, 7, Color.BLACK);
        assertTrue(whiteQueen.canKill(blackRook));
    }
    
    @Test
    public void testCanKillOpponentVertical() {
        Queen whiteQueen = new Queen(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(7, 3, Color.BLACK);
        assertTrue(whiteQueen.canKill(blackKnight));
    }
    
    @Test
    public void testCanKillOpponentDiagonal() {
        Queen whiteQueen = new Queen(3, 3, Color.WHITE);
        Bishop blackBishop = new Bishop(5, 5, Color.BLACK);
        assertTrue(whiteQueen.canKill(blackBishop));
    }
    
    @Test
    public void testCannotKillSameColorPiece() {
        Queen whiteQueen = new Queen(3, 3, Color.WHITE);
        Pawn whitePawn = new Pawn(3, 5, Color.WHITE);
        assertFalse(whiteQueen.canKill(whitePawn));
    }
    
    @Test
    public void testCannotKillPieceInInvalidPosition() {
        Queen whiteQueen = new Queen(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(5, 4, Color.BLACK);
        assertFalse(whiteQueen.canKill(blackKnight));
    }
}