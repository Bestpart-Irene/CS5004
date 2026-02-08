/**
 * Represents a Queen chess piece.
 * A queen can move horizontally, vertically, or diagonally any number of squares.
 */
public class Queen extends ChessPiece {
    
    public Queen(int row, int column, Color color) {
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
        
        // Queen can move horizontally, vertically, or diagonally
        // Horizontal or vertical: one diff is 0
        // Diagonal: both diffs are equal
        return (row == getRow() || col == getColumn() || rowDiff == colDiff);
    }
}