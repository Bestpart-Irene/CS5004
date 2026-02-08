/**
 * Represents a King chess piece.
 * A king can move one square in any direction (horizontally, vertically, or diagonally).
 */
public class King extends ChessPiece {
    
    public King(int row, int column, Color color) {
        super(row, column, color);
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
        
        int rowDiff = Math.abs(row - getRow());
        int colDiff = Math.abs(col - getColumn());
        
        // King can move one square in any direction
        return rowDiff <= 1 && colDiff <= 1;
    }
}