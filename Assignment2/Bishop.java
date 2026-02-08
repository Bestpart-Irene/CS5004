/**
 * Represents a Bishop chess piece.
 * A bishop can move diagonally any number of squares.
 */
public class Bishop extends ChessPiece {
    
    public Bishop(int row, int column, Color color) {
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
        
        // Bishop moves diagonally: absolute difference in rows must equal absolute difference in columns
        int rowDiff = Math.abs(row - getRow());
        int colDiff = Math.abs(col - getColumn());
        
        return rowDiff == colDiff;
    }
}