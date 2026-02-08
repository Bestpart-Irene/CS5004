import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Pawn.
 */
public class PawnTest {
    
    @Test
    public void testConstructorAndGettersWhitePawn() {
        Pawn pawn = new Pawn(1, 0, Color.WHITE);
        assertEquals(1, pawn.getRow());
        assertEquals(0, pawn.getColumn());
        assertEquals(Color.WHITE, pawn.getColor());
    }
    
    @Test
    public void testConstructorAndGettersBlackPawn() {
        Pawn pawn = new Pawn(6, 7, Color.BLACK);
        assertEquals(6, pawn.getRow());
        assertEquals(7, pawn.getColumn());
        assertEquals(Color.BLACK, pawn.getColor());
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
    public void testPawnCannotBeCreatedOutOfBounds() {
        new Pawn(-1, 3, Color.WHITE);
    }
    
    @Test
    public void testWhitePawnCanMoveOneSquareForward() {
        Pawn whitePawn = new Pawn(1, 3, Color.WHITE);
        assertTrue(whitePawn.canMove(2, 3));
    }
    
    @Test
    public void testWhitePawnCanMoveTwoSquaresFromStart() {
        Pawn whitePawn = new Pawn(1, 3, Color.WHITE);
        assertTrue(whitePawn.canMove(3, 3));
    }
    
    @Test
    public void testWhitePawnCannotMoveTwoSquaresAfterStart() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(whitePawn.canMove(5, 3));
    }
    
    @Test
    public void testWhitePawnCanMoveOneSquareAfterStart() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        assertTrue(whitePawn.canMove(4, 3));
    }
    
    @Test
    public void testWhitePawnCannotMoveBackward() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(whitePawn.canMove(2, 3));
        assertFalse(whitePawn.canMove(1, 3));
    }
    
    @Test
    public void testBlackPawnCanMoveOneSquareForward() {
        Pawn blackPawn = new Pawn(6, 3, Color.BLACK);
        assertTrue(blackPawn.canMove(5, 3));
    }
    
    @Test
    public void testBlackPawnCanMoveTwoSquaresFromStart() {
        Pawn blackPawn = new Pawn(6, 3, Color.BLACK);
        assertTrue(blackPawn.canMove(4, 3));
    }
    
    @Test
    public void testBlackPawnCannotMoveTwoSquaresAfterStart() {
        Pawn blackPawn = new Pawn(4, 3, Color.BLACK);
        assertFalse(blackPawn.canMove(2, 3));
    }
    
    @Test
    public void testBlackPawnCanMoveOneSquareAfterStart() {
        Pawn blackPawn = new Pawn(4, 3, Color.BLACK);
        assertTrue(blackPawn.canMove(3, 3));
    }
    
    @Test
    public void testBlackPawnCannotMoveBackward() {
        Pawn blackPawn = new Pawn(4, 3, Color.BLACK);
        assertFalse(blackPawn.canMove(5, 3));
        assertFalse(blackPawn.canMove(6, 3));
    }
    
    @Test
    public void testPawnCannotMoveSideways() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(whitePawn.canMove(3, 4));
        assertFalse(whitePawn.canMove(3, 2));
    }
    
    @Test
    public void testPawnCannotMoveDiagonallyWithCanMove() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(whitePawn.canMove(4, 4));
        assertFalse(whitePawn.canMove(4, 2));
    }
    
    @Test
    public void testPawnCannotMoveToSamePosition() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(pawn.canMove(3, 3));
    }
    
    @Test
    public void testWhitePawnCanKillDiagonallyForwardRight() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(4, 4, Color.BLACK);
        assertTrue(whitePawn.canKill(blackKnight));
    }
    
    @Test
    public void testWhitePawnCanKillDiagonallyForwardLeft() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(4, 2, Color.BLACK);
        assertTrue(whitePawn.canKill(blackKnight));
    }
    
    @Test
    public void testBlackPawnCanKillDiagonallyForwardRight() {
        Pawn blackPawn = new Pawn(5, 3, Color.BLACK);
        Knight whiteKnight = new Knight(4, 4, Color.WHITE);
        assertTrue(blackPawn.canKill(whiteKnight));
    }
    
    @Test
    public void testBlackPawnCanKillDiagonallyForwardLeft() {
        Pawn blackPawn = new Pawn(5, 3, Color.BLACK);
        Knight whiteKnight = new Knight(4, 2, Color.WHITE);
        assertTrue(blackPawn.canKill(whiteKnight));
    }
    
    @Test
    public void testPawnCannotKillStraightAhead() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        Rook blackRook = new Rook(4, 3, Color.BLACK);
        assertFalse(whitePawn.canKill(blackRook));
    }
    
    @Test
    public void testPawnCannotKillSameColorPiece() {
        Pawn whitePawn1 = new Pawn(3, 3, Color.WHITE);
        Pawn whitePawn2 = new Pawn(4, 4, Color.WHITE);
        assertFalse(whitePawn1.canKill(whitePawn2));
    }
    
    @Test
    public void testPawnCannotKillDiagonallyBackward() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(2, 4, Color.BLACK);
        assertFalse(whitePawn.canKill(blackKnight));
    }
    
    @Test
    public void testPawnCannotKillTwoSquaresAway() {
        Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(5, 5, Color.BLACK);
        assertFalse(whitePawn.canKill(blackKnight));
    }
    
    @Test
    public void testPawnCannotKillNull() {
        Pawn pawn = new Pawn(3, 3, Color.WHITE);
        assertFalse(pawn.canKill(null));
    }
    
    @Test
    public void testWhitePawnValidPositions() {
        // These should not throw exceptions
        new Pawn(1, 0, Color.WHITE);
        new Pawn(6, 7, Color.WHITE);
        new Pawn(3, 4, Color.WHITE);
    }
    
    @Test
    public void testBlackPawnValidPositions() {
        // These should not throw exceptions
        new Pawn(0, 0, Color.BLACK);
        new Pawn(6, 7, Color.BLACK);
        new Pawn(3, 4, Color.BLACK);
    }
}