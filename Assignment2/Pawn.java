/**
 * Represents a Pawn chess piece.
 * A pawn can move forward one square (or two from starting position).
 * It can only capture diagonally forward one square.
 */
public class Pawn extends ChessPiece {
    
    public Pawn(int row, int column, Color color) {
        super(row, column, color);
        
        // White pawns cannot be created in row 0 (their royal row)
        // Black pawns cannot be created in row 7 (their royal row)
        if ((color == Color.WHITE && row == 0) || (color == Color.BLACK && row == 7)) {
            throw new IllegalArgumentException("Pawns cannot be created in their royal row");
        }
    }
    
    @Override
    public boolean canMove(int row, int col) {
        // Check if position is valid
        if (!isValidPosition(row, col)) {
            return false;
        }
        
        // Can't move to same position
        if (row == getRow() && col == getColumn()) {
            return false;
        }
        
        // Pawns can only move in their own column (not diagonally) for regular moves
        if (col != getColumn()) {
            return false;
        }
        
        int direction = (getColor() == Color.WHITE) ? 1 : -1; // WHITE moves up (+), BLACK moves down (-)
        int startRow = (getColor() == Color.WHITE) ? 1 : 6;   // Starting row for each color
        
        int rowDiff = row - getRow();
        
        // Move one square forward
        if (rowDiff == direction) {
            return true;
        }
        
        // Move two squares forward from starting position
        if (getRow() == startRow && rowDiff == 2 * direction) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean canKill(ChessPiece piece) {
        // Can only kill opponent's piece
        if (piece == null || piece.getColor() == this.getColor()) {
            return false;
        }
        
        // Pawn kills diagonally forward one square
        int targetRow = piece.getRow();
        int targetCol = piece.getColumn();
        
        int direction = (getColor() == Color.WHITE) ? 1 : -1;
        int rowDiff = targetRow - getRow();
        int colDiff = Math.abs(targetCol - getColumn());
        
        // Must move one square forward and one square sideways
        return rowDiff == direction && colDiff == 1;
    }
}