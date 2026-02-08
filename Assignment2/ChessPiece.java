/**
 * Abstract base class for all chess pieces.
 */
public abstract class ChessPiece implements ChessPieceContract {
    private int row;
    private int column;
    private Color color;
    
    /**
     * Constructor for chess piece.
     * @param row the initial row position (0-7)
     * @param column the initial column position (0-7)
     * @param color the color of the piece
     * @throws IllegalArgumentException if position is out of bounds
     */
    public ChessPiece(int row, int column, Color color) {
        if (row < 0 || row > 7 || column < 0 || column > 7) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        this.row = row;
        this.column = column;
        this.color = color;
    }
    
    @Override
    public int getRow() {
        return row;
    }
    
    @Override
    public int getColumn() {
        return column;
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public abstract boolean canMove(int row, int col);
    
    @Override
    public boolean canKill(ChessPiece piece) {
        // Can only kill opponent's piece
        if (piece == null || piece.getColor() == this.color) {
            return false;
        }
        // Can kill if can move to that position
        return canMove(piece.getRow(), piece.getColumn());
    }
    
    /**
     * Helper method to check if a position is on the board.
     * @param row the row to check
     * @param col the column to check
     * @return true if position is valid, false otherwise
     */
    protected boolean isValidPosition(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }
}