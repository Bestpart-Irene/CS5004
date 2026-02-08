import org.junit.Test;
import static org.junit.Assert.*;

/**
 * General test class for all chess pieces - integration tests.
 */
public class ChessPieceTest {
    
    @Test
    public void testAllPiecesImplementContract() {
        ChessPiece bishop = new Bishop(0, 0, Color.WHITE);
        ChessPiece knight = new Knight(0, 1, Color.WHITE);
        ChessPiece rook = new Rook(0, 7, Color.WHITE);
        ChessPiece queen = new Queen(0, 3, Color.WHITE);
        ChessPiece king = new King(0, 4, Color.WHITE);
        ChessPiece pawn = new Pawn(1, 0, Color.WHITE);
        
        // All pieces should be able to call these methods
        assertTrue(bishop instanceof ChessPieceContract);
        assertTrue(knight instanceof ChessPieceContract);
        assertTrue(rook instanceof ChessPieceContract);
        assertTrue(queen instanceof ChessPieceContract);
        assertTrue(king instanceof ChessPieceContract);
        assertTrue(pawn instanceof ChessPieceContract);
    }
    
    @Test
    public void testPolymorphismWithArray() {
        ChessPiece[] pieces = {
            new Bishop(3, 3, Color.WHITE),
            new Knight(3, 3, Color.WHITE),
            new Rook(3, 3, Color.WHITE),
            new Queen(3, 3, Color.WHITE),
            new King(3, 3, Color.WHITE),
            new Pawn(3, 3, Color.WHITE)
        };
        
        for (ChessPiece piece : pieces) {
            assertEquals(3, piece.getRow());
            assertEquals(3, piece.getColumn());
            assertEquals(Color.WHITE, piece.getColor());
        }
    }
    
    @Test
    public void testBoundaryPositions() {
        // All corners should be valid
        new Rook(0, 0, Color.WHITE);
        new Rook(0, 7, Color.WHITE);
        new Rook(7, 0, Color.BLACK);
        new Rook(7, 7, Color.BLACK);
    }
    
    @Test
    public void testCrossColorKilling() {
        // White pieces can kill black pieces
        Bishop whiteBishop = new Bishop(3, 3, Color.WHITE);
        Knight blackKnight = new Knight(5, 5, Color.BLACK);
        assertTrue(whiteBishop.canKill(blackKnight));
        
        // Black pieces can kill white pieces
        Rook blackRook = new Rook(4, 4, Color.BLACK);
        Queen whiteQueen = new Queen(4, 7, Color.WHITE);
        assertTrue(blackRook.canKill(whiteQueen));
    }
    
    @Test
    public void testSameColorCannotKill() {
        Bishop whiteBishop = new Bishop(3, 3, Color.WHITE);
        Knight whiteKnight = new Knight(5, 5, Color.WHITE);
        assertFalse(whiteBishop.canKill(whiteKnight));
        
        Rook blackRook = new Rook(4, 4, Color.BLACK);
        Queen blackQueen = new Queen(4, 7, Color.BLACK);
        assertFalse(blackRook.canKill(blackQueen));
    }
    
    @Test
    public void testDifferentPiecesHaveDifferentMovementPatterns() {
        Bishop bishop = new Bishop(3, 3, Color.WHITE);
        Knight knight = new Knight(3, 3, Color.WHITE);
        Rook rook = new Rook(3, 3, Color.WHITE);
        
        // Bishop can move diagonally
        assertTrue(bishop.canMove(5, 5));
        // Knight cannot move diagonally (needs L-shape)
        assertFalse(knight.canMove(5, 5));
        // Rook cannot move diagonally
        assertFalse(rook.canMove(5, 5));
        
        // Knight can move in L-shape
        assertTrue(knight.canMove(5, 4));
        // Bishop cannot move in L-shape
        assertFalse(bishop.canMove(5, 4));
        // Rook cannot move in L-shape
        assertFalse(rook.canMove(5, 4));
    }
    
    @Test
    public void testQueenCombinesRookAndBishopMoves() {
        Queen queen = new Queen(3, 3, Color.WHITE);
        
        // Can move like a rook (horizontal/vertical)
        assertTrue(queen.canMove(3, 7)); // horizontal
        assertTrue(queen.canMove(7, 3)); // vertical
        
        // Can move like a bishop (diagonal)
        assertTrue(queen.canMove(7, 7)); // diagonal
    }
    
    @Test
    public void testKingIsLimitedQueen() {
        King king = new King(3, 3, Color.WHITE);
        Queen queen = new Queen(3, 3, Color.WHITE);
        
        // Both can move one square diagonally
        assertTrue(king.canMove(4, 4));
        assertTrue(queen.canMove(4, 4));
        
        // Queen can move multiple squares, king cannot
        assertTrue(queen.canMove(7, 7));
        assertFalse(king.canMove(7, 7));
    }
    
    @Test
    public void testPawnIsUniquePiece() {
        Pawn whitePawn = new Pawn(1, 3, Color.WHITE);
        
        // Pawn has special first move (two squares)
        assertTrue(whitePawn.canMove(3, 3));
        
        // Pawn moves differently than it kills
        Knight blackKnight = new Knight(2, 4, Color.BLACK);
        assertTrue(whitePawn.canKill(blackKnight)); // Can kill diagonally
        assertFalse(whitePawn.canMove(2, 4)); // But cannot move there
    }
    
    @Test
    public void testComplexGameScenario() {
        // Set up a mini scenario
        Queen whiteQueen = new Queen(0, 3, Color.WHITE);
        King blackKing = new King(7, 3, Color.BLACK);
        Pawn whitePawn = new Pawn(5, 2, Color.WHITE);
        Bishop blackBishop = new Bishop(7, 5, Color.BLACK);
        
        // Queen can attack king
        assertTrue(whiteQueen.canKill(blackKing));
        
        // King cannot attack queen (too far)
        assertFalse(blackKing.canKill(whiteQueen));
        
        // Pawn can potentially advance
        assertTrue(whitePawn.canMove(6, 2));
        
        // Bishop can potentially help defend
        assertTrue(blackBishop.canMove(6, 4));
    }
}
