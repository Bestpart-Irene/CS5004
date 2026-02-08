import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for King.
 */
public class KingTest {
    
    @Test
    public void testConstructorAndGetters() {
        King king = new King(0, 4, Color.WHITE);
        assertEquals(0, king.getRow());
        assertEquals(4, king.getColumn());
        assertEquals(Color.WHITE, king.getColor());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidPosition() {
        new King(3, -1, Color.BLACK);
    }
    
    @Test
    public void testCanMoveOneSquareUp() {
        King king = new King(3, 3, Color.WHITE);
        assertTrue(king.canMove(4, 3));
    }
    
    @Test
    public void testCanMoveOneSquareDown() {
        King king = new King(3, 3, Color.WHITE);
        assertTrue(king.canMove(2, 3));
    }
    
    @Test
    public void testCanMoveOneSquareRight() {
        King king = new King(3, 3, Color.WHITE);
        assertTrue(king.canMove(3, 4));
    }
    
    @Test
    public void testCanMoveOneSquareLeft() {
        King king = new King(3, 3, Color.WHITE);
        assertTrue(king.canMove(3, 2));
    }
    
    @Test
    public void testCanMoveOneSquareDiagonalUpRight() {
        King king = new King(3, 3, Color.WHITE);
        assertTrue(king.canMove(4, 4));
    }
    
    @Test
    public void testCanMoveOneSquareDiagonalUpLeft() {
        King king = new King(3, 3, Color.WHITE);
        assertTrue(king.canMove(4, 2));
    }
    
    @Test
    public void testCanMoveOneSquareDiagonalDownRight() {
        King king = new King(3, 3, Color.BLACK);
        assertTrue(king.canMove(2, 4));
    }
    
    @Test
    public void testCanMoveOneSquareDiagonalDownLeft() {
        King king = new King(3, 3, Color.BLACK);
        assertTrue(king.canMove(2, 2));
    }
    
    @Test
    public void testCannotMoveTwoSquaresHorizontal() {
        King king = new King(3, 3, Color.WHITE);
        assertFalse(king.canMove(3, 5));
        assertFalse(king.canMove(3, 1));
    }
    
    @Test
    public void testCannotMoveTwoSquaresVertical() {
        King king = new King(3, 3, Color.WHITE);
        assertFalse(king.canMove(5, 3));
        assertFalse(king.canMove(1, 3));
    }
    
    @Test
    public void testCannotMoveTwoSquaresDiagonal() {
        King king = new King(3, 3, Color.WHITE);
        assertFalse(king.canMove(5, 5));
        assertFalse(king.canMove(1, 1));
    }
    
    @Test
    public void testCannotMoveToSamePosition() {
        King king = new King(3, 3, Color.WHITE);
        assertFalse(king.canMove(3, 3));
    }
    
    @Test
    public void testCannotMoveOutOfBounds() {
        King king = new King(0, 0, Color.WHITE);
        assertFalse(king.canMove(-1, 0));
        assertFalse(king.canMove(0, -1));
        assertFalse(king.canMove(-1, -1));
    }
    
    @Test
    public void testCanMoveFromCorner() {
        King king = new King(0, 0, Color.WHITE);
        assertTrue(king.canMove(0, 1));
        assertTrue(king.canMove(1, 0));
        assertTrue(king.canMove(1, 1));
    }
    
    @Test
    public void testCanKillOpponentPiece() {
        King whiteKing = new King(3, 3, Color.WHITE);
        Queen blackQueen = new Queen(4, 4, Color.BLACK);
        assertTrue(whiteKing.canKill(blackQueen));
    }
    
    @Test
    public void testCannotKillSameColorPiece() {
        King whiteKing = new King(3, 3, Color.WHITE);
        Pawn whitePawn = new Pawn(4, 3, Color.WHITE);
        assertFalse(whiteKing.canKill(whitePawn));
    }
    
    @Test
    public void testCannotKillPieceTwoSquaresAway() {
        King whiteKing = new King(3, 3, Color.WHITE);
        Queen blackQueen = new Queen(5, 5, Color.BLACK);
        assertFalse(whiteKing.canKill(blackQueen));
    }
}