/**
 * Represents a Knight chess piece.
 * A knight moves in an L-shape: 2 squares in one direction and 1 square perpendicular.
 */
public class Knight extends ChessPiece {
    
    public Knight(int row, int column, Color color) {
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
        
        // Knight moves in L-shape: (2,1) or (1,2) in any direction
        int rowDiff = Math.abs(row - getRow());
        int colDiff = Math.abs(col - getColumn());
        
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}