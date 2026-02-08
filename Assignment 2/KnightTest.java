import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Knight.
 */
public class KnightTest {
    
    @Test
    public void testConstructorAndGetters() {
        Knight knight = new Knight(2, 1, Color.BLACK);
        assertEquals(2, knight.getRow());
        assertEquals(1, knight.getColumn());
        assertEquals(Color.BLACK, knight.getColor());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidPosition() {
        new Knight(-1, 5, Color.WHITE);
    }
    
    @Test
    public void testCanMoveAllEightLShapeMoves() {
        Knight knight = new Knight(4, 4, Color.WHITE);
        
        // Test all 8 L-shaped moves
        assertTrue(knight.canMove(6, 5)); // 2 up, 1 right
        assertTrue(knight.canMove(6, 3)); // 2 up, 1 left
        assertTrue(knight.canMove(2, 5)); // 2 down, 1 right
        assertTrue(knight.canMove(2, 3)); // 2 down, 1 left
        assertTrue(knight.canMove(5, 6)); // 1 up, 2 right
        assertTrue(knight.canMove(5, 2)); // 1 up, 2 left
        assertTrue(knight.canMove(3, 6)); // 1 down, 2 right
        assertTrue(knight.canMove(3, 2)); // 1 down, 2 left
    }
    
    @Test
    public void testCannotMoveDiagonal() {
        Knight knight = new Knight(3, 3, Color.WHITE);
        assertFalse(knight.canMove(5, 5));
        assertFalse(knight.canMove(1, 1));
    }
    
    @Test
    public void testCannotMoveHorizontal() {
        Knight knight = new Knight(3, 3, Color.WHITE);
        assertFalse(knight.canMove(3, 5));
        assertFalse(knight.canMove(3, 1));
    }
    
    @Test
    public void testCannotMoveVertical() {
        Knight knight = new Knight(3, 3, Color.WHITE);
        assertFalse(knight.canMove(5, 3));
        assertFalse(knight.canMove(1, 3));
    }
    
    @Test
    public void testCannotMoveInvalidLShape() {
        Knight knight = new Knight(3, 3, Color.WHITE);
        assertFalse(knight.canMove(5, 4)); // Not an L-shape
        assertFalse(knight.canMove(6, 6)); // Not an L-shape
    }
    
    @Test
    public void testCannotMoveToSamePosition() {
        Knight knight = new Knight(3, 3, Color.WHITE);
        assertFalse(knight.canMove(3, 3));
    }
    
    @Test
    public void testCannotMoveOutOfBounds() {
        Knight knight = new Knight(1, 1, Color.BLACK);
        assertFalse(knight.canMove(-1, 2));
        assertFalse(knight.canMove(2, -1));
        assertFalse(knight.canMove(8, 2));
    }
    
    @Test
    public void testCanMoveFromCorner() {
        Knight knight = new Knight(0, 0, Color.WHITE);
        assertTrue(knight.canMove(2, 1));
        assertTrue(knight.canMove(1, 2));
    }
    
    @Test
    public void testCanKillOpponentPiece() {
        Knight whiteKnight = new Knight(3, 3, Color.WHITE);
        Pawn blackPawn = new Pawn(5, 4, Color.BLACK);
        assertTrue(whiteKnight.canKill(blackPawn));
    }
    
    @Test
    public void testCannotKillSameColorPiece() {
        Knight whiteKnight = new Knight(3, 3, Color.WHITE);
        Bishop whiteBishop = new Bishop(5, 4, Color.WHITE);
        assertFalse(whiteKnight.canKill(whiteBishop));
    }
    
    @Test
    public void testCannotKillPieceNotInLShape() {
        Knight whiteKnight = new Knight(3, 3, Color.WHITE);
        Pawn blackPawn = new Pawn(5, 5, Color.BLACK);
        assertFalse(whiteKnight.canKill(blackPawn));
    }
}