/**
 * Represents a Rook chess piece.
 * A rook can move horizontally or vertically any number of squares.
 */
public class Rook extends ChessPiece {
    
    public Rook(int row, int column, Color color) {
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
        
        // Rook moves horizontally or vertically only
        return (row == getRow() || col == getColumn());
    }
}